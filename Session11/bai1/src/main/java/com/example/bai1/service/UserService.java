package com.example.bai1.service;

import com.example.bai1.constant.Role;
import com.example.bai1.model.dto.request.UserLoginDTO;
import com.example.bai1.model.dto.request.UserRegisterDTO;
import com.example.bai1.model.dto.request.VerifyOtpRequest;
import com.example.bai1.model.dto.response.UserLoginResponse;
import com.example.bai1.model.dto.response.UserRegisterResponse;
import com.example.bai1.model.entity.User;
import com.example.bai1.repository.UserRepository;
import com.example.bai1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private JwtProvider jwtProvider;

    public UserRegisterResponse register(UserRegisterDTO userRegisterDTO){
        // Sinh ra mã otp ngẫu nhiên với 6 chữ số
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        User user = User.
                builder()
                .username(userRegisterDTO.getUsername())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .fullName(userRegisterDTO.getFullName())
                .role(Role.USER)
                .enabled(false)
                .email(userRegisterDTO.getEmail())
                .otpCode(otp)
                .otpExpiration(LocalDateTime.now().plusMinutes(5))
                .build();

        mailService.sendMail(userRegisterDTO.getEmail(),otp);
        User newUser = userRepository.save(user);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setFullName(user.getFullName());
        userRegisterResponse.setEmail(user.getEmail());
        userRegisterResponse.setRole(newUser.getRole().toString());
        return userRegisterResponse;
    }

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO){
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        Map<String,String> map = new HashMap<>();
        if(user == null){

            map.put("error","Username hoặc password không chính xác !");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }else {
            if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
                if (user.isEnabled()){
                    UserLoginResponse userLoginResponse = new UserLoginResponse();
                    userLoginResponse.setUsername(user.getUsername());
                    userLoginResponse.setType("Bearer");
                    userLoginResponse.setAccess_token(jwtProvider.generateToken(user));
                    return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);

                }else {
                    map.put("error" , "Vui lòng active tài khoản trước khi đăng nhập !");
                    return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
                }

            } else {
                map.put("error","Username hoặc password không chính xác !");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
        }

    }

    // 2. Kích hoạt (Verify OTP)
    public String verifyAccount(VerifyOtpRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(user == null){
            return "Không tìm thấy tài khoản nào có email là : " + request.getEmail();
        }

        if (user.isEnabled()) {
            return "Tài khoản đã được kích hoạt trước đó rồi.";
        }

        // Check thời gian hết hạn
        if (user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            user.setOtpCode(otp);
            user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
            userRepository.save(user);
            mailService.sendMail(request.getEmail(), otp);
            return "Mã OTP đã hết hạn. Vui lòng nhập mã otp mới";
        }

        // Check mã OTP
        if (!user.getOtpCode().equals(request.getOtp())) {
            throw new RuntimeException("Mã OTP không chính xác.");
        }

        // Kích hoạt thành công
        user.setEnabled(true);
        user.setOtpCode(null); // Xóa OTP cũ
        user.setOtpExpiration(null);
        userRepository.save(user);

        return "Kích hoạt tài khoản thành công! Bạn có thể đăng nhập ngay bây giờ.";
    }
}

package monaco.bot.users.controller;

import lombok.RequiredArgsConstructor;
import monaco.bot.users.dto.UserInfoRequestDto;
import monaco.bot.users.dto.UserInfoResponseDto;
import monaco.bot.users.service.interfaces.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> createApiUser(@RequestBody UserInfoRequestDto request) {
        return new ResponseEntity<>(userInfoService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userInfoService.getUser(id));
    }

}

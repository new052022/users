package monaco.bot.users.controller;

import lombok.RequiredArgsConstructor;
import monaco.bot.users.dto.NewUserExchangeRequestDto;
import monaco.bot.users.dto.UserExchangeResponseDto;
import monaco.bot.users.service.interfaces.UserExchangeInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-exchange-info")
public class UserExchangeInfoController {

    private final UserExchangeInfoService exchangeInfoService;

    @PostMapping
    public ResponseEntity<UserExchangeResponseDto> addExchangeInfoToUser(@RequestBody
                                                                         NewUserExchangeRequestDto request) {
        return new ResponseEntity<>(exchangeInfoService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<UserExchangeResponseDto>> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(exchangeInfoService.getExchanges(userId));
    }

}

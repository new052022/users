package monaco.bot.users.service.interfaces;

import monaco.bot.users.dto.NewUserExchangeRequestDto;
import monaco.bot.users.dto.UserExchangeResponseDto;
import monaco.bot.users.model.UserExchangeInfo;

import java.util.List;

public interface UserExchangeInfoService {

    UserExchangeResponseDto save(NewUserExchangeRequestDto exchangeInfo);

    List<UserExchangeResponseDto> getExchanges(Long userId);

    UserExchangeInfo getExchangeInfoByNameAndUserId(Long userId, String exchange);

    List<UserExchangeInfo> findById(long userId);
}

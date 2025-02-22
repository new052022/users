package monaco.bot.users.service.interfaces;

import monaco.bot.users.dto.UserInfoRequestDto;
import monaco.bot.users.dto.UserInfoResponseDto;
import monaco.bot.users.model.UserInfo;

public interface UserInfoService {

    UserInfoResponseDto save(UserInfoRequestDto userInfo);

    UserInfoResponseDto getUser(Long id);

    UserInfo getUserById(Long userId);
}

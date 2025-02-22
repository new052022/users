package monaco.bot.users.service.interfaces;

import monaco.bot.users.model.Exchange;

import java.util.List;

public interface ExchangeService {

    Exchange getExchangeByName(String name);

    List<Exchange> getAll();
}

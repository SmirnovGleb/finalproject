package by.epam.roulette.commandfactory;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import by.epam.roulette.command.AddPlayerLockCommand;
import by.epam.roulette.command.BetUserCommand;
import by.epam.roulette.command.ChangeEmailCommand;
import by.epam.roulette.command.ChangePasswordCommand;
import by.epam.roulette.command.ChangePercentCommand;
import by.epam.roulette.command.ICommand;
import by.epam.roulette.command.LanguageCommand;
import by.epam.roulette.command.ListAllBetsCommand;
import by.epam.roulette.command.ListBetsCommand;
import by.epam.roulette.command.ListPlayersCommand;
import by.epam.roulette.command.LoginUserCommand;
import by.epam.roulette.command.LogoutCommand;
import by.epam.roulette.command.MessageCommand;
import by.epam.roulette.command.PersonalAccountCommand;
import by.epam.roulette.command.RegistrationUserCommand;
import by.epam.roulette.command.TakeCreditCommand;
import by.epam.roulette.command.ToGameCommand;
import by.epam.roulette.command.UnlockPlayerCommand;
import by.epam.roulette.command.ToAddCardPageCommand;
import by.epam.roulette.command.ToCreditPageCommand;
import by.epam.roulette.command.UserDepositCommand;

/**
 * A factory for creating Command objects.
 */
public class CommandFactory {
	private static CommandFactory instance;
	private static ReentrantLock instanceLock = new ReentrantLock();
	private static AtomicBoolean exist = new AtomicBoolean(false);
	private HashMap<String, ICommand> commands = new HashMap<>();
	{
		commands.put("login", new LoginUserCommand());
		commands.put("game", new BetUserCommand());
		commands.put("language", new LanguageCommand());
		commands.put("registration", new RegistrationUserCommand());
		commands.put("togame", new ToGameCommand());
		commands.put("account", new PersonalAccountCommand());
		commands.put("deposit", new UserDepositCommand());
		commands.put("addcard", new ToAddCardPageCommand());
		commands.put("takecredit", new TakeCreditCommand());
		commands.put("tocredit", new ToCreditPageCommand());
		commands.put("changepassword", new ChangePasswordCommand());
		commands.put("changeemail", new ChangeEmailCommand());
		commands.put("messages", new MessageCommand());
		commands.put("bets", new ListBetsCommand());
		commands.put("changepercent", new ChangePercentCommand());
		commands.put("listplayers", new ListPlayersCommand());
		commands.put("lockplayer", new AddPlayerLockCommand());
		commands.put("unlockplayer", new UnlockPlayerCommand());
		commands.put("allbets", new ListAllBetsCommand());
		commands.put("logout", new LogoutCommand());
	}

	/**
	 * Instantiates a new command factory.
	 */
	private CommandFactory() {

	}

	/**
	 * Gets the single instance of CommandFactory.
	 *
	 * @return single instance of CommandFactory
	 */
	public static CommandFactory getInstance() {
		if (!exist.get()) {
			instanceLock.lock();
			try {
				if (instance == null) {
					instance = new CommandFactory();
					exist.set(true);
				}
			} finally {
				instanceLock.unlock();
			}
		}
		return instance;
	}

	/**
	 * Gets the command.
	 *
	 * @param command
	 *            the command
	 * @return the command
	 */
	public ICommand getCommand(String command) {
		return commands.get(command);
	}
}

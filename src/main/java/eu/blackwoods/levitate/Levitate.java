package eu.blackwoods.levitate;

import org.bukkit.plugin.java.JavaPlugin;

public class Levitate {
	
	private JavaPlugin plugin;
	private CommandRegistry registry;
	private SyntaxValidations syntaxes;
	
	public Levitate(JavaPlugin plugin) {
		this.plugin = plugin;
		SyntaxValidations.registerDefaultSyntax(plugin);
		registry = new CommandRegistry(plugin);
		registry.registerBukkitPermissionHandler();
		registry.registerDefaultHelpMap();
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public CommandRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(CommandRegistry registry) {
		this.registry = registry;
	}

	public SyntaxValidations getSyntaxValidations() {
		return syntaxes;
	}

	public void setSyntaxValidations(SyntaxValidations syntaxes) {
		this.syntaxes = syntaxes;
	}

	/**
	 * Register new command with aliases
	 * @param info CommandInformation with syntax, permission etc
	 * @param aliases Array with aliases
	 * @param handler The CommandHandler which handels the execution of the command
	 */
	public void register(CommandInformation info, String[] aliases, CommandHandler handler) {
		getRegistry().register(info, aliases, handler);
	}
	
	/**
	 * Register new command
	 * @param info CommandInformation with syntax, permission etc
	 * @param handler The CommandHandler which handels the execution of the command
	 */
	public void register(CommandInformation info, CommandHandler handler) {
		getRegistry().register(info, handler);
	}
	
	/**
	 * Register your own syntax.
	 * @param method The base command of your syntax
	 * @param handler The handler to check values against your syntax
	 */
	public static void registerSyntax(String method, SyntaxHandler handler) {
		SyntaxValidations.getSyntaxes().put(method, handler);
	}
	
	/**
	 * Register your own PermissionHandler
	 * @param permissionHandler PermissionHandler wich checks whether the sender has permission to execute the command
	 */
	public void registerPermissionHandler(PermissionHandler permissionHandler) {
		getRegistry().setPermissionHandler(permissionHandler);
	}
	
	/**
	 * Register default HelpMap
	 */
	public void registerDefaultHelpMap() {
		getRegistry().registerDefaultHelpMap();
	}
	
	/**
	 * Register own HelpMaoo
	 * @param helpMap Handles the help-message
	 */
	public void registerHelpMap(HelpMap helpMap) {
		getRegistry().registerHelpMap(helpMap);
	}
	
}

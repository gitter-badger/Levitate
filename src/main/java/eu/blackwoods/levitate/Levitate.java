package eu.blackwoods.levitate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

	public Levitate(JavaPlugin plugin, boolean createYAML) {
		this.plugin = plugin;
		SyntaxValidations.registerDefaultSyntax(plugin);
		registry = new CommandRegistry(plugin);
		registry.registerBukkitPermissionHandler();
		registry.registerDefaultHelpMap();
		if(createYAML) {
			File pluginFolder = plugin.getDataFolder();
			File config = new File(pluginFolder, "messages.yml");
			if(!pluginFolder.exists()) pluginFolder.mkdirs();
			if(!config.exists()) {
				InputStream jarURL = Levitate.class.getResourceAsStream("/messages.yml");
				try {
					if(jarURL == null) {
						config.createNewFile();
					} else {
						copyFile(jarURL, config);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			Message.loadConfig(config);
		}
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
	 * Register own HelpMap
	 * @param helpMap Handles the help-message
	 */
	public void registerHelpMap(HelpMap helpMap) {
		getRegistry().registerHelpMap(helpMap);
	}
	
	public SyntaxValidations getSyntaxes() {
		return syntaxes;
	}

	public void setSyntaxes(SyntaxValidations syntaxes) {
		this.syntaxes = syntaxes;
	}
	
	/**
	 * Copy InputStream to output file
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	private static void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}

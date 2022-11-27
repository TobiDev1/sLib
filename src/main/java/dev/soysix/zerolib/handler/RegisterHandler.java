package dev.soysix.zerolib.handler;

import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RegisterHandler {
   private Plugin plugin;

   public RegisterHandler(Plugin plugin) {
      this.plugin = plugin;
   }

   public void loadListenersFromPackage(String packageName) {
      Iterator var2 = this.getClassesInPackage(packageName).iterator();

      while(var2.hasNext()) {
         Class<?> clazz = (Class)var2.next();
         if (this.isListener(clazz)) {
            try {
               this.plugin.getServer().getPluginManager().registerEvents((Listener)clazz.newInstance(), this.plugin);
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }
      }

   }

   public void loadCommandsFromPackage(String packageName) {
      Iterator var2 = this.getClassesInPackage(packageName).iterator();

      while(var2.hasNext()) {
         Class clazz = (Class)var2.next();

         try {
            clazz.newInstance();
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public boolean isListener(Class<?> clazz) {
      Class[] var2 = clazz.getInterfaces();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Class<?> interfaze = var2[var4];
         if (interfaze == Listener.class) {
            return true;
         }
      }

      return false;
   }

   public Collection<Class<?>> getClassesInPackage(String packageName) {
      Collection<Class<?>> classes = new ArrayList();
      CodeSource codeSource = this.plugin.getClass().getProtectionDomain().getCodeSource();
      URL resource = codeSource.getLocation();
      String relPath = packageName.replace('.', '/');
      String resPath = resource.getPath().replace("%20", " ");
      String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");

      JarFile jarFile;
      try {
         jarFile = new JarFile(jarPath);
      } catch (IOException var17) {
         throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", var17);
      }

      Enumeration entries = jarFile.entries();

      while(entries.hasMoreElements()) {
         JarEntry entry = (JarEntry)entries.nextElement();
         String entryName = entry.getName();
         String className = null;
         if (entryName.endsWith(".class") && entryName.startsWith(relPath) && entryName.length() > relPath.length() + "/".length()) {
            className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
         }

         if (className != null) {
            Class clazz = null;

            try {
               clazz = Class.forName(className);
            } catch (ClassNotFoundException var16) {
               var16.printStackTrace();
            }

            if (clazz != null) {
               classes.add(clazz);
            }
         }
      }

      try {
         jarFile.close();
      } catch (IOException var15) {
         var15.printStackTrace();
      }

      return ImmutableSet.copyOf(classes);
   }
}

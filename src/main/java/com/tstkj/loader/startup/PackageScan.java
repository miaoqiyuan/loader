package com.tstkj.loader.startup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.tstkj.loader.utils.PathConstant;
import com.tstkj.loader.utils.TemplateMap;

public class PackageScan {

	public void scanPackage()
			throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
		String absPath = PathConstant.USER_PATH + (PathConstant.BIN_PACKAGE_PAHT.replace(".", File.separator));
		System.out.println(absPath);
		File file = new File(absPath);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			URLClassLoader cLoader = null;
			try {
				URL[] urls = new URL[] { new URL("file:/" + PathConstant.USER_PATH.replace("\\", "/") + "/") };
				cLoader = new URLClassLoader(urls);
				String fileName = "";
				for (int i = 0; i < files.length; i++) {
					fileName = files[i].getName();
					if (fileName.endsWith("class")) {
						Class<?> c = cLoader
								.loadClass(PathConstant.BIN_PACKAGE_PAHT + files[i].getName().replace(".class", ""));
						Object obj = c.newInstance();
						System.out.println(obj);
						TemplateMap.map.put(files[i].getName().replace(".class", ""), obj);
					}
				}
			} finally {
				try {
					if (cLoader != null) {
						cLoader.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

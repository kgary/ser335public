package ser335.security;

import java.io.*;
import java.security.*;
import java.util.*;

/*
 * Copyright 2020 Tim Lindquist,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: demonstrate using the API to read a keystore created by keytool.
 * This project provides support for running this application so that it
 * demonstrates using the security manager and specifying permissions.
 * The policy file defines read permission to a keystore in the Data
 * directory. 
 *
 * Notes on running: see readme.txt
 * @author Tim Lindquist Tim.Lindquist@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @version March 2020
 */
public class PrintKeyStore {
    public static void main(String[] args) {
	if (args.length != 2) {
	    System.out.println("Usage: ser335.security."+
                               "PrintKeyStore nameOfKeyStore storePwd");
	}else try {
	    System.out.println("Security MGR: "+System.getSecurityManager());
	    KeyStore ks = KeyStore.getInstance("JKS");
	    ks.load(new FileInputStream(args[0]), args[1].toCharArray());
	    for (Enumeration e = ks.aliases() ; e.hasMoreElements() ;) {
		System.out.println(args[0]+ " contains a key for "+
                                   e.nextElement());
	    }
	}catch (Exception e){
	    e.printStackTrace();
	}
    }
}

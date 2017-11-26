package od;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SzyfrPodstawieniowy {

	private SzyfrPodstawieniowy(){ init(); }

	private void init()
	{

	}

	public static String encrypt(String key, String plaintext)
	{
		String ciphertext = "";
		String[] keySpace = new String[key.length()];
                System.out.println("Key:" + key);
		for(int i = 0; i < key.length(); i++) {
                    keySpace[i] = String.valueOf(key.charAt(i));
                    System.out.println("KeySpace i : " + keySpace[i]+" i: " + i);
                }
                
		for(int i = 0; i < plaintext.length(); i++)
		{
			int index = plaintext.charAt(i) - 65;
                        System.out.println("plaintext " + plaintext.charAt(i));
                        System.out.println("index " + index );
			if( index > keySpace.length || index < 0)
			{
				ciphertext += String.valueOf(plaintext.charAt(i));
                                System.out.println("index: " +index +" ciphertext: " + ciphertext + " keySpace.length: " + keySpace.length );
			}
			else
			{
				ciphertext += keySpace[index];
			}
		}

		return ciphertext;
	}

	public static String decrypt(String key, String ciphertext)
	{
		String plaintext = "";
		for(int i = 0; i < ciphertext.length(); i++)
		{
			char character = ciphertext.charAt(i);
			int index = key.indexOf(character);
			int ascii = index + 65;
			if( ascii < 65 || ascii > 122)
			{
				plaintext += String.valueOf(character);
			}
			else
			{
				plaintext += String.valueOf((char)ascii);
			}
		}

		return plaintext;
	}

	public static void main(String[] args) {
		String operation = null, key = null, input = null, output = null;
		if (args.length == 4) {

			if( (operation = args[0]) == "e" ||
					(operation = args[0]) == "d")
			{
				System.err.println("Operation Argument"
						+ " must be e for encrypt or d for decrypt");
				System.exit(1);
			}

			key = args[1];
			if( !( new File(key) ).exists() )
			{
				System.err.println("Key Argument"
						+ " key file must exist");
				System.exit(1);
			}

			input = args[2];
			if( !( new File(input) ).exists())
			{
				System.err.println("Input Argument"
						+ " input file must exist");
				System.exit(1);
			}

			output = args[3];
			if( ( new File(output) ) == null )
			{
				System.err.println("Output Argument"
						+ " output file already exists");
				System.exit(1);
			}

		}
		else
		{
			System.err.println("Nieznany błąd");
			System.exit(1);
		}

		StringBuffer keyString = null, inputString = null, outputString;
		try{
			/*
			 * Read in the Key
			 */
			int ch = -1;
			keyString = new StringBuffer("");
			FileInputStream keyfis = new FileInputStream(new File(key));
			while( (ch = keyfis.read()) != -1) keyString.append((char) ch);
			keyfis.close();

			/*
			 * Read in the input 
			 */
			inputString = new StringBuffer("");
			FileInputStream inputfis = new FileInputStream(new File(input));
			while( (ch = inputfis.read()) != -1) inputString.append((char) ch);
			inputfis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 		

		String result = "";
		int case_op = (operation.equals("e") ? 1 : 2);
		switch( case_op )
		{
		case 1:
		{
                        System.out.println("Szyfrowanie ");
			String ciphertext = result = SzyfrPodstawieniowy.encrypt(keyString.toString(), inputString.toString());
			System.out.println("Key: "+ keyString);
			System.out.println("PlainText: "+ inputString);
			System.out.println("CipherText: "+ ciphertext);
			break;
		}
		case 2:
		{
                        System.out.println("Odszyfrowanie ");
			String plaintext = result = SzyfrPodstawieniowy.decrypt(keyString.toString(), inputString.toString());
			System.out.println("Key: "+ keyString);
			System.out.println("CipherText: "+ inputString);
			System.out.println("PlainText: "+ plaintext);
			break;
		}
		}

		/*
		 * Write in the output
		 */
		try {
			FileOutputStream outputfis = new FileOutputStream(new File(output));
			outputfis.write(result.getBytes());
			outputfis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

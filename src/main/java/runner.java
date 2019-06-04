import org.juniorgang.util.ApplicationContext;
import org.juniorgang.util.HTTPService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class runner {
    public static void main(String[] args) {
        HTTPService service;
        File configs = new File("src/resources/configs.txt");
        if(!configs.exists()){HTTPService.createConfigsFile();}

        HTTPService.createConfigsFile(askForAuths(),"127.0.0.1");
        service = new HTTPService(ApplicationContext.initialize());

        while (true){//main logic loop
            System.out.println("1) create your user (if new)\n" +
                    "2) ");




        }

    }

    public static String askForAuths(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your Username and Password...");
        return sc.findInLine(Pattern.compile(".+ .+"));
    }



}




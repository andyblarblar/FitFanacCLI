import org.juniorgang.util.ApplicationContext;
import org.juniorgang.util.HTTPService;
import org.juniorgang.util.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HTTPService service;
        File configs = new File("src/resources/configs.txt");
        if(!configs.exists()){HTTPService.createConfigsFile();}

        HTTPService.createConfigsFile(askForAuths(),"127.0.0.1:8080");
        service = new HTTPService(ApplicationContext.initialize());

        main: while (true){//main logic loop
            System.out.println("1) create your user (if new)\n" +
                    "2) see your stats\n" +
                    "3) logout \n"+
                    "please enter your section");

            switch (sc.next()){
                case "1": createUser(service);
                break;
                case "2": getUser(service);
                break;
                case "3":logout(service);
                service.setAuths(askForAuths());
                break;
            }

        }

    }

    public static String askForAuths(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your Username and Password...");
        return sc.findInLine(Pattern.compile(".+ .+"));
    }

    public static void createUser(HTTPService service){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your first name and last name");
        String fname = sc.next();
        String lname = sc.next();
        service.doPOST(new User(fname,lname));
    }

    public static void logout(HTTPService service){
        System.out.println("logging out...");
        service.setAuths("");
    }

    public static void getUser(HTTPService service){
        if(!service.testAuths()){System.out.println("username and password are bad");
            return;}

        System.out.println(service.doGET().readEntity(User.class));
    }



}




import org.juniorgang.util.ApplicationContext;
import org.juniorgang.util.HTTPService;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String temp;

        File configs = new File("src/main/resources/configs.txt");
        if(!configs.exists()){ HTTPService.createConfigsFile();}

        HTTPService service = new HTTPService(ApplicationContext.initialize());

        loginScreen(service);

        main: while (true){//main logic loop
            System.out.println(
                    "1) see your account\n" +
                    "2) logout \n" +
                    "3) options\n" +
                            "Q to quit\n"+
                    "please enter your section");

            switch (sc.next()){
                case "1": getUser(service);
                break;

                case "2":logout(service);
                loginScreen(service);
                break;

                case "3": options(service);
                break;

                case "Q":
                    break main;

            }

        }

    }//end main

    /**
     * promts and optains the users username and password
     * @return the unformated auths entered by the user. Is conformed to be .+ .+
     */
    public static String askForAuths(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your Username and Password...");
        return sc.findInLine(Pattern.compile(".+ .+"));
    }

    public static void createUser(HTTPService service){
        service.setAuths(askForAuths());
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

    /**
     * displays the app options
     */
    public static void options(HTTPService service) {

         l: while (true) {
            System.out.println("1) change server ip\n" +
                    "Q to quit\n" +
                    "enter your selection...");
            Scanner sc = new Scanner(System.in);
            String in = sc.next();

            switch (in){
                case "1":
                    System.out.println("enter the servers IP address...");
                    in = sc.next();
                    service.setServerAddress(in);
                    System.out.println("set server address to: "+ service.getServerAddress());
                    break;
                case "Q":
                    break l;

            }
        }
    }

    /**
     * displays the initial menu for logging in
     */
    public static void loginScreen(HTTPService service){
        Scanner sc = new Scanner(System.in);
        String temp = "";
        pre: while (true) {
            System.out.println("press 1 to login, 2 to create a new account, 3 for options, or Q to quit...");
            temp = sc.next();

            switch (temp) {
                case "1":
                    service.setAuths(askForAuths());
                    if (!service.testAuths()) {
                        System.out.println("login unsuccessful, may be wrong, uncreated, or server ip is bad");
                        break;
                    }
                    break pre;

                case "2":
                    try {
                        createUser(service);
                    }
                    catch (Exception e){System.out.println("the server is either offline, or you need to reconfigure the IP");}
                    break;

                case "3":
                    options(service);
                    break;

                case "Q":
                    System.exit(0);//quits program

            }
        }

    }


}




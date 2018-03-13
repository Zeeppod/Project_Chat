package Server.packet;

public class NickColor {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BRIGHT = "\033[0;90m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String PURPLE_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static final String randomString(){
        String[] colors = {RED,GREEN,YELLOW, BLUE, PURPLE, CYAN, WHITE, BLACK_BRIGHT,RED_BRIGHT,GREEN_BRIGHT,YELLOW_BRIGHT,BLUE_BRIGHT, PURPLE_BRIGHT,CYAN_BRIGHT,WHITE_BRIGHT, BLACK};
        int k =(int) (Math.random()*colors.length);
        return colors[k];
    }

}

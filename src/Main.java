public class Main {
    public static final String database_url = "jdbc:mysql://localhost:3306/MFD";
    public static java.sql.Connection con;

    public static void main(String[] args) {
        boolean keepgoing = true;
        Menu menu = new Menu();
        do {
            menu.baseMenu();
            switch (menu.readInt()) {
                case 1:
                    menu.case1();
                    break;
                case 2:
                    menu.case2();
                    break;

                case 3:
                    menu.case3();
                    break;

                case 4:
                    menu.artistData();
                    menu.case4();
                    break;

                case 5:
                    keepgoing = false;
                    break;
            }
        } while (keepgoing);
    }
}

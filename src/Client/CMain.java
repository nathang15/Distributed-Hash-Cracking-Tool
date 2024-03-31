package Client;

public class CMain {

    public static UI ui;

    public static void main(String[]  args) {
        java.awt.EventQueue.invokeLater(() -> {
            CMain.ui = new UI();
            ui.setVisible(true);
        });
    }
}

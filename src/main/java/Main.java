public class Main {

    public static void main(String[] args) {

        String username = "suzun3545@gmail.com";
        String password = "Kilo80.kilo80";
        String targetProfileName = "kunstebru";

        App app = new App();
        app.loginWith(username , password);
        app.navigateToTargetProfile(targetProfileName);
        app.clickFirstPost();
        app.likeAllPost();
        app.tearDown();
    }
}
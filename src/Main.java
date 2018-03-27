public class Main {

    public static void main(String[] args) {

        new Thread(new WebCrawler()).start();

        try {
            new Thread(new WebCrawler()).sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }





}

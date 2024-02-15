package homework;

class Element {
    private void print(String string) {
        for (int i = 0; i < string.length(); i++) {
            System.out.print(string.charAt(i));
            try {
                Thread.sleep(200);
            } catch (InterruptedException exception) {
                System.err.println(exception.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public synchronized void printStar(String chars) {
        this.print(chars);
    }
}

public class Main {
    private static final String[] LOADING_ANIMATION = { ".", "..", "...", "...." };
    private static final int REPEAT_TIMES = 10;
    private static final int ANIMATION_DELAY = 10;

    static void display() {
        for (int i = 0; i < REPEAT_TIMES; i++) {
            System.out.print("\r[*] Loading " + LOADING_ANIMATION[i % LOADING_ANIMATION.length]);
            try {
                Thread.sleep(ANIMATION_DELAY);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\nLoading complete!");
    }

    public static void main(String[] arg) throws InterruptedException {
        display();
        String[] word = {
                "WELCOME TO CSTAD",
                "**************************************************",
                "Nice to meet you all",
                "--------------------------------------------------",
                "downloading complete ..........................100%"
        };
        for (String str : word) {
            Element element = new Element();
            Thread thread = new Thread(() -> element.printStar(str));
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}

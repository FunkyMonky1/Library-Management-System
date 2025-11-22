package org.Library.model;

import java.util.Scanner;

public record Book(String title) {

    public static Book sucheBuch(Scanner scanner) {
        System.out.println("Was für ein Titel hat das Buch: ");
        String title = scanner.nextLine().trim();
        return new Book(title);
    }

}


package com.threelittlepigs.codecool.libraryManager.Utils;

import com.threelittlepigs.codecool.libraryManager.Entities.Book;
import com.threelittlepigs.codecool.libraryManager.Entities.Fine;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Librarian;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Enums.Genre;
import com.threelittlepigs.codecool.libraryManager.Enums.Location;
import com.threelittlepigs.codecool.libraryManager.Services.BookService;
import com.threelittlepigs.codecool.libraryManager.Services.FineService;
import com.threelittlepigs.codecool.libraryManager.Services.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitializerBean {

    public InitializerBean(UserService us, FineService fs, BookService bs) {
        bs.saveBook(new Book("Kis malacok", "György Mátyás", "https://moly.hu/system/covers/big/covers_262639.jpg?1395465884", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "SAD145831"));
        bs.saveBook(new Book("Kis malacok", "György Mátyás", "https://moly.hu/system/covers/big/covers_262639.jpg?1395465884", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "SAD145832"));
        bs.saveBook(new Book("Kis malacok", "György Mátyás", "https://moly.hu/system/covers/big/covers_262639.jpg?1395465884", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "SAD145833"));
        bs.saveBook(new Book("All My Friends Are Dead", "György Mátyás", "https://funnybookcovers.files.wordpress.com/2012/08/avery-monsen-and-jory-john-all-my-friends-are-dead-1.jpg", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "SAD145435"));
        bs.saveBook(new Book("All My Friends Are Dead", "György Mátyás", "https://funnybookcovers.files.wordpress.com/2012/08/avery-monsen-and-jory-john-all-my-friends-are-dead-1.jpg", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "SAD144545"));
        bs.saveBook(new Book("What's Your Poo Telling You", "György Mátyás", "https://www.oddee.com/_media/imgs/articles/a349_b1.jpg", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "273672888"));
        bs.saveBook(new Book("Diary of a Minecraft Zombie", "György Mátyás", "https://imaginationsoup.net/wp-content/uploads/2016/01/Diary-of-a-Minecraft-Zombie.jpg", 1997, "Test", "Béla", Genre.Christian, Location.AQUARIUM, "JUJ222222"));
        bs.saveBook(new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "https://vignette.wikia.nocookie.net/harrypotter/images/c/cb/Philosoper%27s_Stone_New_UK_Cover.jpg/revision/latest?cb=20170109041611", 1997, "Harry Potter and the Philosopher's Stone is the first novel in the Harry Potter series. The book was first published on 26 June, 1997 by Bloomsbury in London, and was later made into a film of the same name.\n Both the book and motion picture were released in the United States under the name Harry Potter and the Sorcerer's Stone, because the publishers were concerned that most American readers would not be familiar enough with the term \"Philosopher's Stone\". However this discussion lead to criticism by the British public who felt as if it shouldn't be changed due to the fact it was an English book.", "Bloomsbury", Genre.Fantasy, Location.AQUARIUM, "9788700631625"));
        bs.saveBook(new Book("Star Wars: Episode I: The Phantom Menace", "Terry Brooks", "https://vignette.wikia.nocookie.net/starwars/images/3/33/ThePhantomMenaceNovelObiWan.jpg/revision/latest?cb=20050806165250", 1999, "A long time ago in a galaxy far, far away, an evil legacy long believed dead is stirring. Even the Jedi are caught by surprise, their attentions focused on the political unrest between the Trade Federation and the Republic. Now the dark side of the Force threatens to overwhelm the light, and only an ancient Jedi prophecy stands between hope and doom for the entire galaxy.\n On the desert world of Tatooine, far from the concerns for the Republic, a slave boy works by day and dreams by night—of being a Jedi Knight and one day traveling the stars to worlds he's only heard of in stories… of finding a way to win freedom from enslavement for himself and his beloved mother. His only hope lies in his extraordinary instincts and his strange gift for understanding the \"rightness\" of things, talents that allow him to be one of the best Podracers on the planet.\n In another part of the galaxy, the Jedi Knight Qui-Gon Jinn and his apprentice, young Obi-Wan Kenobi, are charged with the protection of the Amidala, the young Queen of Naboo, as she seeks to end the siege of her planet by Trade Federation warships. It is this quest that brings Qui-Gon, Obi-Wan, and the Queen's beautiful young handmaiden to the sand-swept streets of Tatooine and the shop where the slave boy Anakin Skywalker toils and dreams. And it is this unexpected meeting that marks the beginning of the drama that will become legend…", "Del Rey", Genre.Science, Location.TERRARIUM, "9780375407437"));
        Librarian amigo = new Librarian("admin", "asdasdasd", "Gabó", "Zana", "gz@gmail.com", "1991-04-20", "CodeCool Nagymező utca 44", "0908070605");
        amigo.setPicture("https://res.cloudinary.com/library-codecool/image/upload/v1519913951/gabk%C3%B3_1.png");
        us.saveUser(amigo);
        Member member =new Member("matykó", "asdasdasd", "Matykó", "Gyuli", "gym@gmail.com", "1997-01-30", "CodeCool Nagymező utca 44", "0908745161");
        member.setPicture("http://res.cloudinary.com/library-codecool/image/upload/v1519914633/matyk%C3%B3_1.png");
        us.saveUser(member);
        Member testMember = new Member("wulf", "asdasdasd", "Matesz", "Bedők", "mb@gmail.com", "1992-01-26", "CodeCool Nagymező utca 44", "0908745161");
        testMember.setPicture("http://res.cloudinary.com/library-codecool/image/upload/v1519914633/m8_2.png");
        us.saveUser(testMember);
        Member testMember2 = new Member("bence", "asdasdasd", "Bence", "Buczkó", "bb@gmail.com", "1990-08-13", "CodeCool Nagymező utca 44", "0908745161");
        testMember2.setPicture("https://res.cloudinary.com/library-codecool/image/upload/v1519914956/bnc_1.png");
        us.saveUser(testMember2);
        Fine regFee = new Fine(15.0, new Date(), new Date(), testMember);
        fs.saveFine(regFee);
        Book book = new Book("Star Wars: Episode I: The Phantom Menace", "Terry Brooks", "https://vignette.wikia.nocookie.net/starwars/images/3/33/ThePhantomMenaceNovelObiWan.jpg/revision/latest?cb=20050806165250", 1999, "A long time ago in a galaxy far, far away, an evil legacy long believed dead is stirring. Even the Jedi are caught by surprise, their attentions focused on the political unrest between the Trade Federation and the Republic. Now the dark side of the Force threatens to overwhelm the light, and only an ancient Jedi prophecy stands between hope and doom for the entire galaxy.\n On the desert world of Tatooine, far from the concerns for the Republic, a slave boy works by day and dreams by night—of being a Jedi Knight and one day traveling the stars to worlds he's only heard of in stories… of finding a way to win freedom from enslavement for himself and his beloved mother. His only hope lies in his extraordinary instincts and his strange gift for understanding the \"rightness\" of things, talents that allow him to be one of the best Podracers on the planet.\n In another part of the galaxy, the Jedi Knight Qui-Gon Jinn and his apprentice, young Obi-Wan Kenobi, are charged with the protection of the Amidala, the young Queen of Naboo, as she seeks to end the siege of her planet by Trade Federation warships. It is this quest that brings Qui-Gon, Obi-Wan, and the Queen's beautiful young handmaiden to the sand-swept streets of Tatooine and the shop where the slave boy Anakin Skywalker toils and dreams. And it is this unexpected meeting that marks the beginning of the drama that will become legend…", "Del Rey", Genre.Science, Location.TERRARIUM, "9780375407438");
        book.setRentedBy(testMember2);
        book.setReservedBy(testMember);
        book.setAvailability(false);
        bs.saveBook(book);
    }
}

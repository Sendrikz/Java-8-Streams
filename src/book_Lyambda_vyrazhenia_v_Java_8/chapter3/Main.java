package book_Lyambda_vyrazhenia_v_Java_8.chapter3;

import book_Lyambda_vyrazhenia_v_Java_8.enteties.Album;
import book_Lyambda_vyrazhenia_v_Java_8.enteties.Artist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    // Task 1

    public static int addUp(Stream<Integer> numbers) {
        // BiFunction<T,Q,R>
        return numbers.reduce(0, Integer::sum);
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());
    }


    public static List<String> nameAndLivePlace(List<Artist> musicians) {
        return musicians.stream()
                .map(member -> member.getName().concat(" ").concat(member.getNationality()))
                .collect(toList());
    }

    public static List<Album> getAlbumsWithMoreThenThreeSongs(List<Album> albums) {
        return albums.stream()
                .filter(album -> album.getTrackList().size() > 3)
                .collect(toList());
    }

    public static void main(String[] args) {

        // Task 2

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Olha", "Ukraine"));

        // rewrite this code using streams
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }

        long totalMembersStreams = artists.stream()
                .flatMap(Artist::getMembers)
                .count();

        long totalMembersStreamsSecondVariant = artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue();


//        Task 3,4
//        a. boolean anyMatch (Predicate<? super Т> predicate); терминальный, высшего порядка
//        Ь. Stream<T> limit (long maxSize); конвеерный, не высшего порядка


//        ошибка, не сможем заинкрементить так как пытаемся изменить переменную из вне
//        Atomicinteger count = new Atomicinteger(O);
//        List<String> origins = album.musicians()
//                .forEach(musician -> count.incAndGet();)

    }

    // Task 5

    public static long countLowercaseLetters(String word) {
        return word.chars()
                .filter(Character::isLowerCase)
                .count();
    }

    // Task 6
    public static Optional<String> getLongestLowerCaseString(List<String> strings) {
        return strings.stream()
                .max(Comparator.comparing(Main::countLowercaseLetters));
    }

}

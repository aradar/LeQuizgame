package de.spitak.amazinggame;

import java.util.Arrays;

import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Item;
import de.spitak.amazinggame.model.Option;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by phil on 1/25/17.
 */

public class DummyGameGenerator {
    public static Game createCustomGame() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();


        Game game = realm.createObject(Game.class);
        game.setName("Cheers!");
        game.setDescription("A simple game about drunk people and where to find them.");

        Option start = realm.createObject(Option.class);
        start.setTitle("Cheers!");
        start.setDescription("Dein Schädel brummt wie Sau. Du befindest dich in… ja wo " +
                "befindest du dich eigentlich? Das sieht aus wie das Innere eines Schrankes. " +
                "Versuche herauszufinden, was passiert ist. Was ist dein nächster Schritt?");

        Option escapeCloset = realm.createObject(Option.class);
        escapeCloset.setTitle("Der Raum");
        escapeCloset.setDescription("Na das bringt doch schon etwas Licht ins dunkel! " +
                "Du bist in einem Zimmer. Erkennen kannst du trotzdem kaum etwas" +
                ", da das Licht aus ist. Du gehst erstmal zum Lichtschalter und betätigst ihn. " +
                "Es bleibt dunkel. Wahrscheinlich defekt. Du merkst das der Boden aus Dielen ist," +
                " da jeder Schritt von dir quietscht. Okay und was nun? Wo gehts hin? Da du dich " +
                "wie bereits erwähnt in eine Raum befindest, setzt du dir als Ziel, diesen zu" +
                "verlassen. Du solltest dich also..");
        escapeCloset.setHint("gehe aus Schrank");

        Option stayInCloset = realm.createObject(Option.class);
        stayInCloset.setTitle("Erstmal eine Runde chillen");
        stayInCloset.setDescription("Um auf den ganzen Schwachsinn erst einmal klar zu kommen," +
                "entschließt du dich noch ein paar Minuten im Schrank zu verweilen. Ist ja " +
                "auch gemütlich hier zwischen den ganzen Kleidungsstücken. Spannend! Gehts " +
                "jetzt weiter?");
        stayInCloset.setHint("chille im Schrank");


        Option lookAroundShelf = realm.createObject(Option.class);
        lookAroundShelf.setTitle("Im Raum umsehen");
        lookAroundShelf.setDescription("Du siehst dich in diesem Zimmer etwas um. Viel kannst du " +
                "nicht erkennen, da das Licht seinen Ausgangszustand beibehalten hat. Du summst" +
                " jetzt den Song aus dieser TUI Werbung, weil er dir seit gestern Abend einfach" +
                " nicht mehr aus dem Kopf gehen will.\n\nDa sich deine Augen an die Dunkelheit " +
                "gewöhnt haben erkennst du langsam die Umrisse eines Regals.");
        lookAroundShelf.setHint("umsehen");

        Option lookAroundDesk = realm.createObject(Option.class);
        lookAroundDesk.setTitle("Im Raum umsehen");
        lookAroundDesk.setDescription("Du siehst dich in diesem Zimmer etwas um. Viel kannst du " +
                "nicht erkennen, da das Licht seinen Ausgangszustand beibehalten hat. Du summst" +
                " jetzt den Song aus dieser TUI Werbung, weil er dir seit gestern Abend einfach" +
                " nicht mehr aus dem Kopf gehen will.\n\nDu erkennst am Ende des Zimmers das " +
                "Flimmern eines Monitors. Das klingt interessant oder?");
        lookAroundDesk.setHint("erneut umsehen");

        Option lookAroundDoor = realm.createObject(Option.class);
        lookAroundDoor.setTitle("Im Raum umsehen");
        lookAroundDoor.setDescription("Du siehst dich in diesem Zimmer etwas um. Viel kannst du " +
                "nicht erkennen, da das Licht seinen Ausgangszustand beibehalten hat. Du summst" +
                " jetzt den Song aus dieser TUI Werbung, weil er dir seit gestern Abend einfach" +
                " nicht mehr aus dem Kopf gehen will.\n\nAh eine Tür! Nichts wie hin.. oder?");
        lookAroundDoor.setHint("da hinten umsehen");

        Option shelf = realm.createObject(Option.class);
        shelf.setTitle("Hey ein Billiregal...");
        shelf.setDescription("\"... Ikea - hej hur mår du. Oder wie man das ausspricht. Ich wusste" +
                " nicht, dass ich schwedisch kann. Was wollte ich gerade eigentlich machen? Achso" +
                " das Regal. Genau! Das sollte ich durchsuchen.\"");
        shelf.setHint("zum Regal");

        Option shelf2 = realm.createObject(Option.class);
        shelf2.setTitle("Hust.. Hust.. Hust");
        shelf2.setDescription("Als du an einem staubigen Karton ziehst fällt von diesem plötzlich" +
                "eine Brechstange herunter! Zum Glück trägst du einen Helm, denn Sicherheit geht " +
                "vor!");
        shelf2.setHint("durchsuchen");

        Option desk = realm.createObject(Option.class);
        desk.setTitle("Ein stinknormaler Schreibtisch eben");
        desk.setDescription("Als du näher an den Schreibtisch heran trittst erkennst du," +
                " dass auf dem dort manifestierten Rechner noch eine Uraltversion von Windows Vista " +
                "läuft. Ein eiskalter Schauer erfüllt deinen Rücken. Wo bist du nur gelandet? " +
                "Du weist nur eines. Du musst hier so schnell wie möglich raus. Das muss die Wohnung " +
                "eines Verrückten sein. \"Die eine Schublade hier scheint zu klemmen." +
                "Vielleicht kann ich Sie mit etwas aufbrechen?\"");
        desk.setHint("es leuchtet hell");

        Option desk2 = realm.createObject(Option.class);
        desk2.setTitle("Knaaaack, Bruch, Schnaaarz");
        desk2.setDescription("Als du die Schublade aufbrichst kommt dir ein Schlüssel entgegen.");
        desk2.setHint("aufbrechen, aufbrechen, aufbrechen!");


        Option door = realm.createObject(Option.class);
        door.setTitle("Da eine Tür!");
        door.setDescription("Der Weg ins Freie. Das ist deine Chance. Du versucht den Türknauf zu " +
                "drehen, aber es scheint abgesperrt zu sein. Verflucht. Du beginnst wie ein" +
                " Verrückter zu klopfen und den Türknauf noch energischer zu drehen" +
                " aber es regt sich nichts. Dir fällt das verdreckte Schloss der Tür ins Auge." +
                "\n>Jetzt ehrlich wird das hier so ein sinnloses Escape-Room-Spiel. Das habe ich " +
                "bestimmt Torsten und den Anderen zu verdanken< grübelst du nachdenklich.\n Seis drum" +
                "Vielleicht kann ich die Tür irgendwie öffnen?");
        door.setHint("oh, eine Tür");

        Option freedom = realm.createObject(Option.class);
        freedom.setTitle("Geschafft");
        freedom.setDescription("Glückwunsch du bist enkommen! War doch garnicht so hart wie vermutet " +
                "oder? Na dann.. war schön mit dir! Bis später Peter.");
        freedom.setHint("fertig, wie jetzt?");

        Item key = realm.createObject(Item.class);
        key.setName("uralter Schlüssel");
        key.setDescription("Wow was ist denn das für ein altes Ding? Das gehört wohl eher in ein" +
                "Museum. Und damit man etwas öffnen können? Naja ich weis ja nicht.");

        Item crowbar = realm.createObject(Item.class);
        crowbar.setName("Brechstange");
        crowbar.setDescription("Solide, was soll man da groß zu beschreiben. Jeder weiß was eine" +
                "Brechstange ist oder etwa nicht?");

        start.setLeft(escapeCloset);
        start.setRight(stayInCloset);

        stayInCloset.setParent(start);
        escapeCloset.setParent(start);

        stayInCloset.setLeft(escapeCloset);
        stayInCloset.setRight(stayInCloset);

        escapeCloset.setLeft(lookAroundShelf);
        escapeCloset.setRight(lookAroundShelf);

        lookAroundShelf.setParent(escapeCloset);
        lookAroundShelf.setLeft(lookAroundDesk);
        lookAroundShelf.setRight(shelf);

        lookAroundDesk.setParent(lookAroundShelf);
        lookAroundDesk.setLeft(lookAroundDoor);
        lookAroundDesk.setRight(desk);

        lookAroundDoor.setParent(lookAroundDesk);
        lookAroundDoor.setLeft(lookAroundShelf);
        lookAroundDoor.setRight(door);

        shelf.setParent(lookAroundShelf);
        shelf.setLeft(lookAroundShelf);
        shelf.setRight(shelf2);

        desk.setParent(lookAroundDesk);
        desk.setLeft(lookAroundShelf);
        desk.setRight(desk2);

        door.setParent(lookAroundDoor);
        door.setLeft(lookAroundShelf);
        door.setRight(freedom);

        shelf2.setParent(shelf);
        desk2.setParent(desk);
        freedom.setParent(door);

        shelf2.getLoot().add(crowbar);
        desk2.getLoot().add(key);
        desk2.getRequirements().add(crowbar);
        freedom.getRequirements().add(key);

        freedom.setCompleted(true);
        freedom.setBackstepBlocked(true);
        escapeCloset.setBackstepBlocked(true);
        stayInCloset.setBackstepBlocked(true);


        game.getOptions().addAll(
                Arrays.asList(
                        start,
                        escapeCloset,
                        stayInCloset,
                        lookAroundShelf,
                        lookAroundDesk,
                        lookAroundDoor,
                        desk2,
                        freedom,
                        shelf2));

        game.setCurrentOption(start);

        realm.commitTransaction();
        //return realm.where(Game.class).findFirst();
        return game;
    }
}

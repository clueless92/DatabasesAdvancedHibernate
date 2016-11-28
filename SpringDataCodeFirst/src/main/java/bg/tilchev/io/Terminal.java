package bg.tilchev.io;

import bg.tilchev.models.Town;
import bg.tilchev.models.User;
import bg.tilchev.services.TownService;
import bg.tilchev.services.UserService;
import bg.tilchev.services.WizzardDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;

/**
 * Created by Todor Ilchev on 2016-11-01.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final WizzardDepositService wizzardDepositService;

    private final UserService userService;

    private final TownService townService;

    @Autowired
    public Terminal(WizzardDepositService wizzardDepositService, UserService userService, TownService townService) {
        this.wizzardDepositService = wizzardDepositService;
        this.userService = userService;
        this.townService = townService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        WizzardDeposit dumbledore = new WizzardDeposit();
//        dumbledore.setFirstName("Albus");
//        dumbledore.setLastName("Dumbledore");
//        dumbledore.setAge(150);
//        dumbledore.setMagicWandCreator("Antoich Peverell");
//        dumbledore.setMagicWandSize((short) 15);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2016, 10, 20);
//        Date depositStart = calendar.getTime();
//        dumbledore.setDepositStartDate(depositStart);
//        calendar.set(2020, 10, 20);
//        Date depositEnd = calendar.getTime();
//        dumbledore.setExpirationDate(depositEnd);
//        dumbledore.setDepositAmount(new BigDecimal(2000.24d));
//        dumbledore.setDepositCharge(new BigDecimal(0.2d));
//        dumbledore.setDepositExpired(false);
//
//        this.wizzardDepositService.persist(dumbledore);

        User pesho = new User();
        pesho.setAge(25);
        pesho.setEmail("pilchev@abv.bg");
        pesho.setLastTimeLoggedIn(new Date());
        pesho.setPassword("PESHOpass1*");
        pesho.setRegisteredOn(new Date());
        pesho.setUsername("Pesho");
        pesho.setFirstName("Peter");
        pesho.setLastName("Ivanov");
        File picture = new File("res/smallPic.jpg");
        byte[] pictureArray = Files.readAllBytes(picture.toPath());
        pesho.setProfilePicture(pictureArray);
        pesho.addFriend(pesho);

        Town currentTown = new Town("Sofia", "Bulgaria");
        Town homeTown = new Town("Shumen", "Bulgaria");
        this.townService.persist(currentTown);
        this.townService.persist(homeTown);

        pesho.setHomeTown(homeTown);
        pesho.setCurrentTown(currentTown);
        currentTown.getUsersCurrentlyInTown().add(pesho);
        homeTown.getUsersBornInTown().add(pesho);
        pesho.setDeleted(false);
        this.userService.persist(pesho);


        for (User user : this.userService.findUsersByEmail("pilchev@abv.bg")) {
            System.out.println(user.getFullName());
        }
//
//        int n = this.userService.countOfPicturesAbove(new byte[100]);
//        System.out.println(n);
    }
}

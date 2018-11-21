package com.ttn.reap.reapbootcamp.service;

import com.ttn.reap.reapbootcamp.entity.User;
import com.ttn.reap.reapbootcamp.entity.UserRecogonize;
import com.ttn.reap.reapbootcamp.repository.BadgeRepository;
import com.ttn.reap.reapbootcamp.repository.UserRecogonizeRepository;
import com.ttn.reap.reapbootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class UserService {


    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRecogonizeRepository userRecogonizeRepository;


    public void saveOnSignUp(User user) {
        user.setRoleId(1);
        saveBadges(user);
        //saveEarned(user);

        user.setEarnedGold(0);
        user.setEarnedSilver(0);
        user.setEarnedBronze(0);
        //calculatePoints(user);
        userRepository.save(user);
    }


    public void saveBadges(User user) {
        if (user.getRoleId() == 1) {
            user.setGoldCount(3);
            user.setSilverCount(2);
            user.setBronzeCount(1);
        }

        if (user.getRoleId() == 2) {
            user.setGoldCount(6);
            user.setSilverCount(3);
            user.setBronzeCount(2);
        }

        if (user.getRoleId() == 3) {
            user.setGoldCount(9);
            user.setSilverCount(6);
            user.setBronzeCount(3);
        }
    }

    public void saveBadgeType(UserRecogonize userRecogonize) {
        if (userRecogonize.getBadgeId() == 1) {
            userRecogonize.setBadgeType("Gold");
        } else if (userRecogonize.getBadgeId() == 2) {
            userRecogonize.setBadgeType("Silver");
        } else if (userRecogonize.getBadgeId() == 3) {
            userRecogonize.setBadgeType("Bronze");
        }
    }

    /*public Integer calculatePoints(User user)
    {
        Iterator<Badge> badgeIterator = badgeRepository.findAll().iterator();
        Integer points=0;
        if(badgeIterator.hasNext()){
            Badge badge = badgeIterator.next();
                if(badge.getBadge().equalsIgnoreCase("gold")){
                    points += badge.getPoints() * user.getGoldCount();
                }
                else if(badge.getBadge().equalsIgnoreCase("silver")){
                    points += badge.getPoints() * user.getSilverCount();
                }
                else if(badge.getBadge().equalsIgnoreCase("bronze")){
                    points += badge.getPoints() * user.getBronzeCount();
                }
        }
        return points;
    }*/

    public Integer updatePoints(UserRecogonize userRecogonize, User user) {

        int badgeId = userRecogonize.getBadgeId();
        if (badgeId == 1) {
            user.setGoldCount(user.getGoldCount() - 1);

        } else if (badgeId == 2) {
            user.setSilverCount(user.getSilverCount() - 1);
        } else if (badgeId == 3) {
            user.setBronzeCount(user.getBronzeCount() - 1);
        }
        return null;

    }

    public Integer updateDestination(UserRecogonize userRecogonize, User user) {
        String dest = userRecogonize.getDestEmail();
        int badgeId = userRecogonize.getBadgeId();

        User u = userRepository.findByEmail(dest);
        if (badgeId == 1)
            u.setEarnedGold(u.getEarnedGold() + 1);
        else if (badgeId == 2)
            u.setEarnedSilver(u.getEarnedSilver() + 1);
        else if (badgeId == 3)
            u.setEarnedBronze(u.getEarnedBronze() + 1);
        return null;
    }

    public User findUserByEmail(String email) {
        List<User> user = userRepository.findByEmailId(email);
        if (user != null && user.size() > 0) {
            System.out.println(user.get(0));
        }
        return userRepository.findByEmail(email);
    }


    public UserRecogonize save(UserRecogonize userRecogonize) {
        return userRecogonizeRepository.save(userRecogonize);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserRecogonize> find() {
        List<UserRecogonize> userRecogonizeList = userRecogonizeRepository.findRecogonize();
        return userRecogonizeList;
    }

    public List<UserRecogonize> findSharedBadges(User user) {
        List<UserRecogonize> userRecogonizeList1 = userRecogonizeRepository.findShared(user.getEmail());
        return userRecogonizeList1;
    }

    public List<UserRecogonize> findReceivedBadges(User user) {
        List<UserRecogonize> userRecogonizeList2 = userRecogonizeRepository.findReceived(user.getEmail());
        return userRecogonizeList2;
    }

    public List<UserRecogonize> findAllBadges(User user) {
        List<UserRecogonize> userRecogonizeList3 = userRecogonizeRepository.findAllBadges(user.getEmail());
        return userRecogonizeList3;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }


}

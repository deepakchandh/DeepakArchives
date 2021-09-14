package com.java.designPattern;

// https://javabypatel.blogspot.com/2016/06/observer-design-pattern-java.html
import java.util.ArrayList;
import java.util.List;

interface Observers{
    void notifyIt(String post);
}

class SocialMediaNotif implements  Observers{

    @Override
    public void notifyIt(String post) {
        System.out.println("SocialMediaNotif" + post);
    }
}

class SubscriberUsedNotif implements Observers{

    @Override
    public void notifyIt(String post) {
        System.out.println("SubscriberUsedNotif" + post);
    }
}

interface Subjects{
    void addObserver(Observers observer);
    void removeObserver(Observers observer);
    void notifyObservers();
}


class MediumBlogPost implements Subjects{
    private String post;
    private List<Observers> listOfObserver = new ArrayList<Observers>();

    @Override
    public void addObserver(Observers observer) {
        listOfObserver.add(observer);
    }

    @Override
    public void removeObserver(Observers observer) {
        listOfObserver.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observers observer : listOfObserver) {
            observer.notifyIt(post);
        }
    }

    public void newPost(String post) {
        this.post = post;
        notifyObservers();
    }
}

public class ObserverDesignPattern {
    public static void main(String... args){
        SocialMediaNotif socialMediaNotif = new SocialMediaNotif();
        SubscriberUsedNotif subscriberUsedNotif = new SubscriberUsedNotif();


        MediumBlogPost mediumBlogPost = new MediumBlogPost();
        mediumBlogPost.addObserver(socialMediaNotif);
        mediumBlogPost.addObserver(subscriberUsedNotif);

        mediumBlogPost.newPost("Hello Peter");
    }

}

package com.example;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

import java.io.*;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyActionsApp extends DialogflowApp {



    @ForIntent("Default Welcome Intent")
    public ActionResponse welcome(ActionRequest request) {

        ResponseBuilder responseBuilder = getResponseBuilder(request);
            responseBuilder.add("Hello, welcome to Bennett University. What can I do for you?");
             return responseBuilder.build();
    }

    @ForIntent("BreakfastIntent")
    public ActionResponse breakfast(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Today's breakfast is pav bhaji, bread and butter, corn flakes, as well as tea and coffee. Have a great day!").endConversation();
            return responseBuilder.build();
    }

    @ForIntent("LunchIntent")
    public ActionResponse lunch(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Lunch today is rice, aloo gravy, curd and butter roti. There's probably a queue, so better hurry!").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("DinnerIntent")
    public ActionResponse dinner(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("For dinner today there's rice, white sauce pasta, butter roti, curd, as well as ice cream. Enjoy!").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("NextClass")
    public ActionResponse nextclass(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Your next class is Java by Prof Rishav Singh, at 9:30 AM, Ground Floor Lecture Hall.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("RoomClean")
    public ActionResponse roomclean(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        String roomNo = request.getParameter("number").toString();
        int rNo = Integer.parseInt(roomNo);

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("hellohitesh123@gmail.com", "Example User"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("hn5298@bennett.edu.in", "Admin"));
            msg.setSubject("Cleaning requested");
            msg.setText("Cleaning requested in room "+ rNo+".");
            Transport.send(msg);
        } catch (UnsupportedEncodingException | MessagingException ignored) {

        }

        responseBuilder.add("I've let housekeeping know. They'll be at room "+rNo+" soon.");
        return responseBuilder.build();
    }

    @ForIntent("LibraryClose")
    public ActionResponse libclose(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The library closes at 11 PM. Don't forget to take your I.D Card along.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("LaundryClose")
    public ActionResponse lauclose(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The laundry closes at 7 PM.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("PoolIntent")
    public ActionResponse pooli(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The pool's closed only on Wednesdays, so by all means do go for a swim today! It's open till 9 PM.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("End")
    public ActionResponse pooli(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Talk to you later, champ.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("HelpIntent")
    public ActionResponse helpi(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("I'm the Bennett University helpdesk. You can ask me things like what's for lunch, when the laundry closes, or to even get your room cleaned.").endConversation();
        return responseBuilder.build();
    }





}



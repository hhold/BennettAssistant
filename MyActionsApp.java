package com.example;

import com.google.actions.api.ActionRequest; //importing Google Assistant libraries
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.wildbit.java.postmark.Postmark; //importing Postmark libraries to send emails
import com.wildbit.java.postmark.client.ApiClient;
import com.wildbit.java.postmark.client.data.model.message.Message;
import com.wildbit.java.postmark.client.data.model.message.MessageResponse;
import com.wildbit.java.postmark.client.exception.PostmarkException;

import java.io.IOException;
import java.*;
import java.util.Objects;


public class MyActionsApp extends DialogflowApp {



    @ForIntent("Default Welcome Intent") //Welcome Intro Response
    public ActionResponse welcome(ActionRequest request) {  //gets request from Google Assistant as request object

        ResponseBuilder responseBuilder = getResponseBuilder(request);
            responseBuilder.add("Hello, welcome to Bennett University. What can I do for you?");
             return responseBuilder.build(); //generates response to be sent back to Google Assistant
    }

    @ForIntent("BreakfastIntent") //Response for Breakfast
    public ActionResponse breakfast(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Today's breakfast is pav bhaji, bread and butter, corn flakes, as well as tea and coffee. Have a great day!").endConversation();
            return responseBuilder.build();
    }

    @ForIntent("LunchIntent")   //Response for Lunch
    public ActionResponse lunch(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Lunch today is rice, aloo gravy, curd and butter roti. There's probably a queue, so better hurry!").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("DinnerIntent")  //Response for Dinner
    public ActionResponse dinner(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("For dinner today there's rice, white sauce pasta, butter roti, curd, as well as ice cream. Enjoy!").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("NextClass") //Response for Next Class Query
    public ActionResponse nextclass(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Your next class is Java by Prof Rishav Singh, at 9:30 AM, Ground Floor Lecture Hall.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("RoomClean") //Room Clean send email to admin dept
    public ActionResponse roomclean(ActionRequest request) throws IOException, PostmarkException {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        String roomNo = null;
        try {
            roomNo = Objects.requireNonNull(request.getParameter("number")).toString();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        ApiClient client = Postmark.getApiClient("897f114d-18ce-4c8f-9a90-4282fc994b4a");
        String mes = "Cleaning requested by room " + roomNo;
        Message message = new Message("hn5298@bennett.edu.in", "hn5298@bennett.edu.in", "Cleaning requested", mes);
        MessageResponse respon = client.deliverMessage(message);

        responseBuilder.add("I've let housekeeping know. They'll be at room " + roomNo + " soon.");
        return responseBuilder.build();
    }

    @ForIntent("LibraryClose")  //Response for library closing
    public ActionResponse libclose(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The library closes at 11 PM. Don't forget to take your I.D Card along.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("LaundryClose")  //Response for when laundry closes
    public ActionResponse lauclose(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The laundry closes at 7 PM.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("PoolIntent")    //Response when pool closes
    public ActionResponse pooli(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("The pool's closed only on Wednesdays, so by all means do go for a swim today! It's open till 9 PM.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("End")   //Goodbye response
    public ActionResponse end(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Talk to you later, champ.").endConversation();
        return responseBuilder.build();
    }

    @ForIntent("HelpIntent")    //Respond with instructions
    public ActionResponse helpi(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("I'm the Bennett University helpdesk. You can ask me things like what's for lunch, when the laundry closes, or to even get your room cleaned.").endConversation();
        return responseBuilder.build();
    }





}



#Flint

##Work In Progress

Android [common intents](http://developer.android.com/guide/components/intents-common.html) with [fluent interface](https://en.wikipedia.org/wiki/Fluent_interface).

Done:
 - Create an alarm
 - Create a timer
 - Swow all alarms
 
To do:
 - Add a calendar event
 - Capture a picture or video
 - Start a camera app in still image mode
 - Start a camera app in video mode
 - Select a contact
 - Select specific contact data
 - View a contact
 - Edit an existing contact
 - Insert a contact
 - Compose an email with optional attachments
 - Retrieve a specific type of file
 - Open a specific type of file
 - Call a car
 - Show a location on a map
 - Play a media file
 - Play music based on a search query
 - Create a note
 - Initiate a phone call
 - Search using a specific app
 - Perform a web search
 - Open a specific section of Settings
 - Compose an SMS/MMS message with attachment
 - Load a web URL
 
##Example
 
     Flint.alarm(hour).minutes(minutes).days(Calendar.MONDAY).silent().start(activity);
     
     instead of:
     
     Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
     intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
     intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
     ArrayList<Intent> days = new ArrayList<>(1);
     days.add(Calendar.MONDAY);
     intent.putExtra(AlarmClock.EXTRA_DAYS, days);
     intent.putExtra(AlarmClock.EXTRA_RINGTONE, AlarmClock.VALUE_RINGTONE_SILENT);
     startActivity(intent);

##Documentation

Javadoc is not currently available, please use [tests](https://github.com/dcampogiani/Flint/tree/master/app/src/androidTest/java/com/danielecampogiani/flint) to see examples.

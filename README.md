# Women Safety App:

Script:
  - Test Case:
    People can't see small font. They have 24x7 either mobile data or WiFi always "ON". Considering Today's data rates, everyone has mobile data packs. Cabs ,such as Uber, Ola, etc, nowadays do provide with WiFi facility. Person in trouble can call the emergency services, but, instead they came to my App. Hence, my App should do some other stuff rather than just call the emergency services.
    
  - App Usage:
    App is Simple to understand, consists of just 2 Buttons:

    1. Yellow:
      Yellow is for the situation in which you think the situation might get worse, for example, if you are going with someone in the car and the driver takes a left turn instead of right turn. You can press the Yellow button, there is also a section to add some useful information such as car number, under Metro Pillar No (as Location might get affect when stand under some obstacles, such as Metro bridge in the case) or something else, which you think would relevant to the police. It will send your IMEI number, location, time, along with that comment every 5 seconds, and you can still add more information at any given point of time.

    2. Red:
      Red Button is for the situation, where there is no denying that you are in an emergency situation. Functioning is similar to Yellow, i.e. sending IMEI number, location, time along with the "new" as this new page will also be to get the comment from the user and send it, but will do the above explained every half a second. On pressing on this button, the nearby police station will be informed almost immediately as they will received a text providing a link. That link, when opened will show a Google Map along with some markers that has the details of corresponding user in emergency along with their comments.

Features:
- Ability to add personalized comments.
- IMEI number Tracking along with Timestamp of each location.
- Table shown in PostgresSql alongwith.
- Easy Large Scale Implementation.

Bugs that can be solved:
- None in my testing
- You may encounter, please do tell me

Bugs that can't be solved:
- Database/Table from Police with verified Phone numbers
This is here in this section because I can't acquire these official numbers from my side. I need someone in Police Dept to help me out.

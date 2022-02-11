import React, { useState, useEffect } from "react";

import FamousSpeechImage from "./Assets/famousspeeches.png"
import PlayImage from "./Assets/play.png"
import PauseImage from "./Assets/pauseButton.png"
import Audio1 from "./Assets/FamousSpeeches/WinstonChruchill.mp3"
import Audio2 from "./Assets/FamousSpeeches/BarackObama.mp3"
import Audio3 from "./Assets/FamousSpeeches/DenzelWashington.mp3"
import Audio4 from "./Assets/FamousSpeeches/MartinLutherKingJr.mp3"

const useAudio = url => {
    const [audio] = useState(new Audio(url));
    const [playing, setPlaying] = useState(false);
  
    const toggle = () => setPlaying(!playing);
  
    useEffect(() => {
        playing ? audio.play() : audio.pause();
      },
      [playing]
    );
  
    useEffect(() => {
      audio.addEventListener('ended', () => setPlaying(false));
      return () => {
        audio.removeEventListener('ended', () => setPlaying(false));
      };
    }, []);
  
    return [playing, toggle];
  };

function FamousSpeechItem(props) {

    const [playing, toggle] = useAudio(props.audioPath);

    const playbuttonClicked = () => {
        props.updateSpeech(props.text)
        toggle()
    }

    return (
        <div className="famous-list-item-container">
            <div className="famous-list-item-text">
                <div className="famous-list-item-text-title">{props.speech}</div>
                <div className="famous-list-item-text-subtitle">{props.description}</div>
            </div>
            <img className="famous-speech-item-image" onClick={playbuttonClicked} src = 
            {(() => 
                {
                    if (playing) return PauseImage;
                    else return PlayImage;
                }
            )()} alt ="play-pause-btn"/>
        </div>
    );
}


function FamousSpeeches(props) {

    const speeches = {
        "Winston Churchil" : {text: "The disastrous military events which have happened during the past fortnight have not come to me with any sense of surprise. Indeed, I indicated a fortnight ago as clearly as I could to the House that the worst possibilities were open; and I made it perfectly clear then that whatever happened in France would make no difference to the resolve of Britain and the British Empire to fight on, 'if necessary for years, if necessary alone.' During the last few days we have successfully brought off the great majority of the troops we had on the line of communication in France; and seven-eighths of the troops we have sent to France since the beginning of the war–that is to say, about 350,000 out of 400,000 men–are safely back in this country. Others are still fighting with the French, and fighting with considerable suc- cess in their local encounters against the enemy. We have also brought back a great mass of stores, rifles and munitions of all kinds which had been accumulated in France during the last nine months. \n We have, therefore, in this Island today a very large and powerful military force. This force comprises all our best-trained and our finest troops, including scores of thousands of those who have already meas- ured their quality against the Germans and found themselves at no disadvantage. We have under arms at the present time in this Island over a million and a quarter men. Behind these we have the Local Defense Volunteers, numbering half a million, only a portion of whom, however, are yet armed with rifles or other firearms. We have incorporated into our Defense Forces every man for whom we have a weapon. We expect very large additions to our weapons in the near future, and in preparation for this we intend forthwith to call up, drill and train further large numbers. Those who are not called up, or else are employed during the vast business of munitions production in all its branches–and their ramifications are innumerable–will serve their country best by remaining at their ordinary work until they receive their summons. We have also over here Dominions armies. The Canadians had actually landed in France, but have now been safely withdrawn, much disappointed, but in perfect order, with all their artillery and equipment. And these very high-class forces from the Dominions will now take part in the defence of the Mother Country. \n It seems to me that as far as sea-borne invasion on a great scale is concerned, we are far more capable of meeting it today than we were at many periods in the last war and during the early months of this war, before our other troops were trained, and while the B.E.F. had proceeded abroad. Now, the Navy have never pretended to be able to prevent raids by bodies of 5,000 or 10,000 men flung suddenly across and thrown ashore at several points on the coast some dark night or foggy morning. The efficacy of sea power, especially under modern conditions, depends upon the invading force being of large size. It has to be of large size, in view of our military strength, to be of any use. If it is of large size, then the Navy have something they can find and meet and, as it were, bite on. Now, we must remember that even five divisions, however lightly equipped, would require 200 to 250 ships, and with modern air reconnaissance and photography it would not be easy to collect such an armada, marshal it, and conduct it across the sea without any powerful naval forces to escort it; and there would be very great possibilities, to put it mildly, that this armada would be intercepted long before it reached the coast, and all the men drowned in the sea or, at the worst blown to pieces with their equipment while they were trying to land. We also have a great system of minefields, recently strongly reinforced. \n This brings me, naturally, to the great question of invasion from the air, and of the impending struggle between the British and Ger- man Air Forces. It seems quite clear that no invasion on a scale beyond the capacity of our land forces to crush speedily is likely to take place from the air until our Air Force has been definitely overpowered. In the meantime, there may be raids by parachute troops and attempted descents of airborne soldiers. We should be able to give those gentry a warm reception both in the air and on the ground, if they reach it in any condition to continue the dispute. But the great question is: Can we break Hitler’s air weapon? Now, of course, it is a very great pity that we have not got an Air Force at least equal to that of the most powerful enemy within striking distance of these shores. But we have a very powerful Air Force which has proved itself far superior in quality, both in men and in many types of machine, to what we have met so far in the numerous and fierce air battles which have been fought with the Germans. In France, where we were at a considerable disadvantage and lost many machines on the ground when they were standing round the aerodromes, we were accustomed to inflict in the air losses of as much as two and two-and-a-half to one. In the fighting over Dunkirk, which was a sort of no-man’s-land, we undoubtedly beat the German Air Force, and gained the mastery of the local air, inflicting here a loss of three or four to one. \n There remains, of course, the danger of bombing attacks, which will certainly be made very soon upon us by the bomber forces of the enemy. It is true that the German bomber force is superior in numbers to ours; but we have a very large bomber force also, which we shall use to strike at military targets in Germany without intermission. I do not at all underrate the severity of the ordeal which lies before us; but I believe our countrymen will show themselves capable of standing up to it, like the brave men of Barcelona, and will be able to stand up to it, and carry on in spite of it, at least as well as any other people in the world. Much will depend upon this; every man and every woman will have the chance to show the finest qualities of their race, and render the highest service to their cause. For all of us, at this time, whatever our sphere, our station, our occupation or our duties, it will be a help to remember the famous lines: \n\n He nothing common did or mean, Upon that memorable scene. \n\n I have thought it right upon this occasion to give the House and the country some indication of the solid, practical grounds upon which we base our inflexible resolve to continue the war. There are a good many people who say, “Never mind. Win or lose, sink or swim, better die than submit to tyranny–and such a tyranny.” And I do not disso- ciate myself from them. But I can assure them that our professional advisers of the three Services unitedly advise that we should carry on the war, and that there are good and reasonable hopes of final victory. We have fully informed and consulted all the self-governing Domin- ions, these great communities far beyond the oceans who have been built up on our laws and on our civilization, and who are absolutely free to choose their course, but are absolutely devoted to the ancient Motherland, and who feel themselves inspired by the same emotions which lead me to stake our all upon duty and honor. We have fully consulted them, and I have received from their Prime Ministers, Mr. Mackenzie King of Canada, Mr. Menzies of Australia, Mr. Fraser of New Zealand, and General Smuts of South Africa–that wonderful man, with his immense profound mind, and his eye watching from a distance the whole panorama of European affairs–I have received from all these eminent men, who all have Governments behind them elected on wide franchises, who are all there because they represent the will of their people, messages couched in the most moving terms in which they endorse our decision to fight on, and declare themselves ready to share our fortunes and to persevere to the end.\n During the first four years of the last war the Allies experienced nothing but disaster and disappointment. That was our constant fear: one blow after another, terrible losses, frightful dangers. Everything miscarried. And yet at the end of those four years the morale of the Allies was higher than that of the Germans, who had moved from one aggressive triumph to another, and who stood everywhere triumphant invaders of the lands into which they had broken. During that war we repeatedly asked ourselves the question: How are we going to win? and no one was able ever to answer it with much precision, until at the end, quite suddenly, quite unexpectedly, our terrible foe collapsed before us, and we were so glutted with victory that in our folly we threw it away. \n What General Weygand called the Battle of France is over. I ex- pect that the Battle of Britain is about to begin. Upon this battle de- pends the survival of Christian civilization. Upon it depends our own British life, and the long continuity of our institutions and our Empire. The whole fury and might of the enemy must very soon be turned on us. Hitler knows that he will have to break us in this Island or lose the war. If we can stand up to him, all Europe may be free and the life of the world may move forward into broad, sunlit uplands. But if we fail, then the whole world, including the United States, including all thatwe have known and cared for, will sink into the abyss of a new Dark Age made more sinister, and perhaps more protracted, by the lights of perverted science. Let us therefore brace ourselves to our duties, and so bear ourselves that, if the British Empire and its Commonwealth last for a thousand years, men will still say, 'This was their finest hour.'", audioPath: Audio1, description: "Title of the speech"},   
        "Barack Obama" : {text: "Don’t tell me words don’t matter. I have a dream – just words words. We hold these truths to be self evident that all men are created equal – just words. We have nothing to fear but fear itself – just words, just speeches. It’s true that speeches don’t solve all problems, but what is also true is that if we can’t inspire our country to believe again, then it doesn’t matter how many policies and plans we have, and that is why I’m running for president of the United States of America, and that’s why we just won 8 elections straight because the American people want to believe in change again. Don’t tell me words don’t matter!", audioPath: Audio2, description: "Title of the speech"},   
        "Martin Luther King Jr" : {text :`I have a dream that one day this nation will rise up and live out the true meaning of its creed: We hold these truths to be self-evident, that all men are created equal. I have a dream that one day on the red hills of Georgia, the sons of former slaves and the sons of former slave owners will be able to sit down together at the table of brotherhood. I have a dream that one day even the state of Mississippi, a state sweltering with the heat of injustice, sweltering with the heat of oppression, will be transformed into an oasis of freedom and justice. I have a dream that my four little children will one day live in a nation where they will not be judged by the color of their skin but by the content of their character. I have a dream today! I have a dream that one day, down in Alabama, with its vicious racists, with its governor having his lips dripping with the words of interposition and nullification -- one day right there in Alabama little black boys and black girls will be able to join hands with little white boys and white girls as sisters and brothers. I have a dream today! I have a dream that one day every valley shall be exalted, and every hill and mountain shall be made low, the rough places will be made plain, and the crooked places will be made straight.
        and the glory of the Lord shall be revealed and all flesh shall see it together. \
        ...and so let freedom ring from the prodigious hilltops of New Hampshire. Let freedom ring from the mighty mountains of New York. \
        Let freedom ring from the heightening Alleghenies of Pennsylvania. \
        Let freedom ring from the snow-capped Rockies of Colorado. \
        Let freedom ring from the curvaceous slopes of California. But not only that: \
        Let freedom ring from Stone Mountain of Georgia. \
        Let freedom ring from Lookout Mountain of Tennessee. Let freedom ring from every hill and molehill of Mississippi. From every mountainside, let freedom ring. \
        And when this happens, when we allow freedom ring, when we let it ring from every village and every hamlet, from every state and every city, we will be able to speed up that day when all of God's children, black men and white men, Jews and Gentiles, Protestants and Catholics, will be able to join hands and sing in the words of the old Negro spiritual: \
        Free at last! Free at last! \
        Thank God Almighty, we are free at last! `, audioPath: Audio4, description: "Title of the speech"},
        "Denzel Washington" : {text: `Thinking about the speech, I figured the best way to keep your attention would be to talk about some really, juicy Hollywood stuff. I thought I could start with me and Russell Crowe getting into some arguments on the set of American Gangster…
        … but no. You’re a group of high-minded intellectuals. You’re not interested in that.Or how about that “private” moment I had with Angelina Jolie half naked in her dressing room backstage at the Oscars?… Who wants to hear about that?
        I don’t think so. This is an Ivy League school. Angelina Jolie in her dressing room…?No, this is Penn. That stuff wouldn’t go over well here. Maybe at Drexel—but not here. I’m in trouble now.I was back to square one—and feeling the pressure. So now you’re probably thinking—if it was gonna be this difficult, why’d I even accept today’s invitation in the first place?Well, you know my son goes here. That’s a good reason. And I always like to check to see how my money’s being spent.And I’m sure there’s some parents out there who can relate to what I’m talking about!And there were other good reasons for me to show up.Sure, I got an Academy Award… but I never had something called “Magic Meatballs” after waiting in line for half an hour at a food truck.
        True, I’ve talked face-to-face with President Obama… but I never met a guy named “Kweeder” who sings bad cover songs at Smokes on a Tuesday night.Yes, I’ve played a detective battling demons… but I’ve never been to a school in my life where the squirrel population has gone bananas, breaking into the dorm rooms and taking over campus. I think I’ve even seen some carrying books on the way to class!So I had to be here. I had to come… even though I was afraid I might make a fool of myself.In fact… if you really want to know the truth: 
        I had to come… exactly because I might make a fool of myself.What am I talking about?Well, here it is:I’ve found that nothing in life is worthwhile unless you take risks.Nothing.Nelson Mandela said: “There is no passion to be found playing small—in settling for a life that’s less than the one you’re capable of living.”
        I’m sure in your experiences—in school… in applying to college… in picking your major… in deciding what you want to do with life—people have told you to make sure you have something to “fall back on.”But I’ve never understood that concept, having something to fall back on. If I’m going to fall, I don’t want to fall back on anything, except my faith. I want to fall… forward. 
        At least I figure that way I’ll see what I’m about to hit.
        Fall forward.
        Here’s what I mean: 
        Reggie Jackson struck out twenty-six-hundred times in his career—the most in the history of baseball. 
        But you don’t hear about the strikeouts. People remember the home runs.
        Fall forward.
        Thomas Edison conducted 1,000 failed experiments. Did you know that?  
        I didn’t either—because #1,001 was the light bulb.
        Fall forward.
        Every failed experiment is one step closer to success. 
        You’ve got to take risks. And I’m sure you’ve probably heard that before.
        But I want to talk about why it’s so important.
        I’ve got three reasons—and then you can pick up your iPhones.
        
        First… you will fail at some point in your life. Accept it. You will lose.  You will embarrass yourself. You will suck at something. There is no doubt about it.
        
        That’s probably not a traditional message for a graduation ceremony.  But, hey, I’m telling you—embrace it.
        
        Because it’s inevitable.
        
        And I should know: In the acting business, you fail all the time.
        
        Early in my career, I auditioned for a part in a Broadway musical. A perfect role for me, I thought—except for the fact that I can’t sing.
        
        So I’m in the wings, about to go on stage but the guy in front of me is singing like Pavarotti and I am just shrinking getting smaller and smaller...
        
        So I come out with my little sheet music and it was “Just My Imagination” by the Temptations, that’s what I came up with.
        
        So I hand it to the accompanist, and she looks at it and looks at me and looks at the director, so I start to sing and they’re not saying anything. I think I must be getting better, so I start getting into it.
        
        But after the first verse, the director cuts me off: “Thank you. Thank you very much, you’ll be hearing from me.”
        
        The next part of the audition is the acting part. I figure, I can’t sing, but I know I can act.
        
        But the guy I was paired with to do the scene couldn’t be more overdramatic and over-the top.
        
        Suffice to say, I didn’t get the part.
        
        But here’s the thing: I didn’t quit. I didn’t fall back.
        
        I walked out of there to prepare for the next audition, and the next audition, and the next one. I prayed and I prayed, but I continued to fail, and I failed, and I failed.
        
        But it didn’t matter. Because you know what? You hang around a barbershop long enough—sooner or later you will get a haircut.
        
        You will catch a break.
        
        Last year I did a play called Fences on Broadway and I won a Tony Award. And I didn’t have to sing for it, by the way. 
        
        And here’s the kicker—it was at the Court Theater, the same theater where I failed that first audition 30 years prior.
        
        The point is, every graduate here today has the training and the talent to succeed.
        
        But do you have guts to fail?
        
        Here’s my second point about failure: 
        
        If you don’t fail… you’re not even trying.
        
        My wife told me this expression: “To get something you never had, you have to do something you never did.”
        
        Les Brown, a motivational speaker, made an analogy about this.
        
        Imagine you’re on your deathbed—and standing around your bed are the ghosts representing your unfilled potential.
        
        The ghosts of the ideas you never acted on. The ghosts of the talents you didn’t use.
        
        And they’re standing around your bed. Angry. Disappointed. Upset. 
        
        “We came to you because you could have brought us to life,” they say.  “And now we go to the grave together.”
        
        So I ask you today: How many ghosts are going to be around your bed when your time comes?
        
        You invested a lot in your education. And people invested in you.
        
        And let me tell you, the world needs your talents. 
        
        Man, does it ever.
        
        I just got back from four months of filming in South Africa—beautiful country, but there are places with terrible poverty that need help. 
        And Africa is just the tip of the iceberg.
        
        The Middle East needs your help. Japan needs your help. Alabama and Tennessee need your help. Louisiana needs your help. Philadelphia needs your help.
        
        The world needs a lot—and we need it from you, the young people.  
        
        So get out there. Give it everything you’ve got—whether it’s your time, your talent, your prayers, or your treasure.
        
        Because remember this: You’ll never see a U-haul behind a hearse. 
        
        You can’t take it with you. The Ancient Egyptians tried it—and all they got was robbed!
        
        So what are you going to do with what you have? And I’m not talking how much you have.
        
        Some of you are business majors. Some of you are theologians, nurses, sociologists. Some of you have money. Some of you have patience. Some have kindness.  Some have love. Some of you have the gift of long-suffering.
        
        Whatever it is… what are you going to do with what you have?
        
        Now here’s my last point about failure:
        
        Sometimes it’s the best way to figure out where you’re going.
        
        Your life will never be a straight path.
        
        I began at Fordham University as a pre-med student. That lasted until I took a course called “Cardiac Morphogenesis.” 
        
        I couldn’t pronounce it… and I couldn’t pass it.
        
        Then I decided to go pre-law. Then journalism.
        
        With no academic focus, my grades took off in their own direction: down.
        
        My GPA was 1.8 one semester, and the university very politely suggested it might be better to take some time off. 
        
        I was 20 years old, at my lowest point.
        
        And then one day—and I remember the exact day: March 27th, 1975—I was helping out in the beauty shop my mother owned in Mount Vernon.  
        An older woman who belonged to my mother’s church, one of the elders of the town, was in there getting her hair done and kept giving me these strange looks.
        
        She finally took the drier off her head and said something to me I’ll never forget:
        
        “Young boy,” she said. “I have a spiritual prophecy: you are going to travel the world and speak to millions of people.”
        
        Like a wise-ass, I’m thinking to myself: “Does she got anything in that crystal ball about me getting back to college in the fall?”
        
        But maybe she was on to something. Because later that summer, while working as a counselor at a YMCA camp in Connecticut, we put on a talent show for the campers. 
        
        After the show, another counselor came up to me and asked: “Have you ever thought of acting? You should. You’re good at that.”
        
        When I got back to Fordham that fall I changed my major once again —for the last time.
        
        And in the years that followed—just as that woman getting her hair done predicted—I have traveled the world and I have spoken to millions of people through my movies. 
        
        Millions who—up ‘till today—I couldn’t see while I was talking to them.
        
        But I do see you today. And I’m encouraged by what I see. I’m strengthened by what I see. I love what I see.
        
        Let me conclude with one final point. Many years ago I did this movie called Philadelphia. We actually filmed some scenes right here on campus.
        
        Philadelphia came out in 1993, when most of you were probably still crawling around in diapers. Some of the professors, too.
        
        But it’s a good movie. Rent it on Netflix. I get 23 cents every time you do. Tell your friends, too!
        
        It’s about a man, played by Tom Hanks, who’s fired from his law firm because he has AIDS. 
        
        He wants to sue the firm, but no one’s willing to represent him until a homophobic, ambulance-chasing lawyer—played by yours truly—takes on the case.
        
        In a way, if you watch the movie, you’ll see everything I’m talking about today.
        
        You’ll see what I mean about taking risks or being willing to fail.
        
        Because taking a risk is not just about going for a job. 
        
        It’s also about knowing what you know and what you don’t know. It’s about being open to people and ideas.
        
        Over the course of the film, the character I play begins to take risks. He slowly overcomes his fears, and ultimately his heart becomes flooded with love.
        
        And I can’t think of a better message as we send you off today.
        
        To not only take risks, but to be open to life. 
        
        To accept new views and to be open to new opinions.
        
        To be willing to speak at commencement at one of the country’s best universities… even though you’re scared stiff.
        
        While it may be frightening, it will also be rewarding.
        
        Because the chances you take… the people you meet… the people you love...the faith that you have—that’s what’s going to define your life.
        
        So… members of the class of 2011: This is your mission:
        
        When you leave the friendly confines of West Philly: Never be discouraged. Never hold back. Give everything you’ve got.
        
        And when you fall throughout life—and maybe even tonight after a few too many glasses of champagne—fall forward. 
        
        Congratulations, I love you, God bless you, I respect you.`, audioPath: Audio3, description: "Title of the speech"},
    }

    return(
        <div className="famous-speech-container">
            <div className="famous-speech-header">
                <div className="famous-speech-header-text">
                    <div className="famous-speech-header-title">Famous Speeches</div>
                    <div className="famous-speech-header-subtitle">(Hear and practice)</div>
                </div>
                <img src={FamousSpeechImage} className="famous-speech-header-image"/>
            </div> 
            {   
                Object.keys(speeches).map((key, index) => ( 
                    <FamousSpeechItem speech = {key} text = {speeches[key].text} audioPath = {speeches[key].audioPath} description = {speeches[key].description} updateSpeech = {props.updateSpeech} updateAudioPath = {props.updateAudioPath}/>  
                    ))
            }
        </div>  
    );
}

export default FamousSpeeches;
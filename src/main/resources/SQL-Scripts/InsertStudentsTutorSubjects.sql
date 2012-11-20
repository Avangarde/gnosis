INSERT INTO `gnosis`.`student`(`id`,`email`,`firstName`,`lastName`,`password`,`url_Photo`,`userName`,`Program_code`)
VALUES
(1,"earojasc@unal.edu.co","Edward","Rojas","admin","http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","earojasc",2543),
(2,"jmmartinez@unal.edu.co","Juan","Martinez","admin","http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jmmartinez",2543),
(3,"javergarav@unal.edu.co","Jeisooon","Vergara","admin","http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","javergarav",2543),
(4,"jlpuertox@unal.edu.co","Jose","Puerto","admin","http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jlpuertox",2543),
(5,"sacortesh@unal.edu.co","Sergio","Cortés","admin","http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","sacortesh",2543); 

INSERT INTO `gnosis`.`tutor`(`id`,`number_students`,`number_votes`,`published_resources`,`question_received`,`reputation`,`url_Photo`,`userName`,`studentId`)
VALUES
(1,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","earojasc",1),
(2,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jmmartinez",2),
(3,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","javergarav",3),
(4,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jlpuertox",4),
(5,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","sacortesh",5);

INSERT INTO `gnosis`.`tutor_subject`(`id`,`number_students`,`number_votes`,`published_resources`,`question_received`,`reputation`,`url_Photo`,`userName`,`SubjectCode`,`TutorId`)
VALUES
(1,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","earojasc",2016702,1),
(2,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jmmartinez",2016506,2),
(3,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","javergarav",1000004,3),
(4,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","jlpuertox",1000004,4),
(5,0,0,0,0,0,"http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg","sacortesh",1000004,5);

;

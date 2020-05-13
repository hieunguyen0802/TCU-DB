use tcu
db.dropDatabase()
use tcu

// Faculty
var sanchez = {
	'name' : 'Antonio Sanchez',
	'site' : 'http://csfaculty.tcu.edu/asanchez/'
};

var mei = {
	'name' : 'Bo Mei',
	'site' : 'http://personal.tcu.edu/bmei/'
};

var scherger = {
	'name' : 'Mike Scherger',
	'site' : 'https://sites.google.com/site/drmichaelscherger/'
};

db.faculty.insert(sanchez);
db.faculty.insert(mei);
db.faculty.insert(scherger);

var sanchez_id, mei_id, scherger_id;
db.faculty.find().forEach( function(doc){
	if(doc.name === 'Antonio Sanchez') {
		sanchez_id = doc._id;
	}
	if(doc.name === 'Bo Mei') {
		mei_id = doc._id;
	}
	if(doc.name === 'Mike Scherger') {
		scherger_id = doc._id;
	}
});


// Courses
var intro = {
	'name' : 'Intro to Programming',
	'course_no' : 10403,
	'credits' : 3,
	'prereqs' : [],
	'availability' : [
		{
			'teacher' : sanchez_id,
			'section' : 15,
			'days' : ['Tuesday', 'Thursday'],
			'time' : {
				'start' : '9:30am',
				'end' : '10:50am'
			}
		},
		{
			'teacher' : sanchez_id,
			'section' : 45,
			'days' : ['Tuesday', 'Thursday'],
			'time' : {
				'start' : '12:30pm',
				'end' : '1:50pm'
			}
		},
		{
			'teacher' : mei_id,
			'section' : 55,
			'days' : ['Tuesday', 'Thursday'],
			'time' : {
				'start' : '2:00pm',
				'end' : '3:20pm'
			}
		}
	],
	'textbook' : [
		{
			'title' : 'Learning Java Gui Applications',
			'author' : ['Philip Conrod', 'Lou Tylee']
		}
	],
	'description' : 'Introduction to computers, problem solving, algorithm design, and programming techniques. Includes what a computer is and is not, problem organization, data representation, and how to utilize the computer to solve problems. Numerical and non-numerical problems are solved in a high-level programming language.'
};

var techniques = {
	'name' : 'Techniques in Programming',
	'course_no' : 20203,
	'credits' : 3,
	'prereqs' : [],
	'availability' : [
		{
			'teacher' : sanchez_id,
			'section' : 35,
			'days' : ['Tuesday', 'Thursday'],
			'time' : {
				'start' : '11:00am',
				'end' : '12:20pm'
			}
		}
	],
	'textbook' : [
		{
			'title' : 'Big Java',
			'author' : ['Cay Horstmann']
		}
	],
	'description' : 'A study of program design, development and programming techniques. Structured programming with problems selected from list processing, string processing, and file manipulation.'
};

var data_structures = {
	'name' : 'Data Structures',
	'course_no' : 20803,
	'credits' : 3,
	'prereqs' : [],
	'availability' : [
		{
			'teacher' : scherger_id,
			'section' : 40,
			'days' : ['Monday', 'Wednesday'],
			'time' : {
				'start' : '12:30pm',
				'end' : '1:50pm'
			}
		},
		{
			'teacher' : mei_id,
			'section' : 65,
			'days' : ['Tuesday', 'Thursday'],
			'time' : {
				'start' : '3:30pm',
				'end' : '4:50pm'
			}
		}
	],
	'textbook' : [],
	'description' : 'Basic concepts of data. Linear lists, strings, and arrays. Representation of trees and graphs. Storage systems and structures. Symbol tables and searching techniques, sorting techniques. Formal specification of data structures and data structures in programming languages.'
};

db.courses.insert(intro);
db.courses.insert(techniques);
db.courses.insert(data_structures);

var intro_id, tech_id, data_id;
db.courses.find().forEach( function(doc){
	if(doc.name === 'Intro to Programming') {
		intro_id = doc._id;
	}
	if(doc.name === 'Techniques in Programming') {
		tech_id = doc._id;
	}
	if(doc.name === 'Data Structures') {
		data_id = doc._id;
	}
});


// Students
var stu_1 = {
	'name' : {
        'first' : 'John',
        'last' : 'Doe'
    },
    'stu_id' : 12345678,
    'birthday' : ISODate("1996-04-15T05:00:00Z"),
    'gpa' : '3.6',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science', 'Mathematics'],
    'minor' : [],
    'courses' : [
        {
            'course_id' : intro_id,
            'section' : 45,
            'grade' : 'A'
        }
    ]
};

var stu_2 = {
	'name' : {
        'first' : 'Joe',
        'last' : 'Blogs'
    },
    'stu_id' : 83456789,
    'birthday' : ISODate("1996-11-15T05:00:00Z"),
    'gpa' : '3.7',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science'],
    'minor' : ['Mathematics'],
    'courses' : [
        {
            'course_id' : intro_id,
            'section' : 55,
            'grade' : 'B+'
        }
    ]
};

var stu_3 = {
	'name' : {
        'first' : 'Jane',
        'last' : 'Doe'
    },
    'stu_id' : 23456789,
    'birthday' : ISODate("1995-05-15T05:00:00Z"),
    'gpa' : '3.8',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science'],
    'minor' : ['Mathematics'],
    'courses' : [
        {
            'course_id' : tech_id,
            'section' : 35,
            'grade' : 'B+'
        }
    ]
};

var stu_4 = {
	'name' : {
        'first' : 'Mike',
        'last' : 'Scott'
    },
    'stu_id' : 33456789,
    'birthday' : ISODate("1995-02-15T05:00:00Z"),
    'gpa' : '3.4',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science'],
    'minor' : [],
    'courses' : [
        {
            'course_id' : tech_id,
            'section' : 35,
            'grade' : 'B'
        }
    ]
};

var stu_5 = {
	'name' : {
        'first' : 'John',
        'last' : 'Snow'
    },
    'stu_id' : 63456789,
    'birthday' : ISODate("1995-01-15T05:00:00Z"),
    'gpa' : '3.9',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science'],
    'minor' : [],
    'courses' : [
        {
            'course_id' : data_id,
            'section' : 40,
            'grade' : 'B'
        }
    ]
};

var stu_6 = {
	'name' : {
        'first' : 'Harry',
        'last' : 'Potter'
    },
    'stu_id' : 63856789,
    'birthday' : ISODate("1995-01-15T05:00:00Z"),
    'gpa' : '3.2',
    'degree_type' : 'B.S.',
    'major' : ['Computer Science'],
    'minor' : ['Mathematics'],
    'courses' : [
        {
            'course_id' : data_id,
            'section' : 65,
            'grade' : 'B'
        }
    ]
};

db.students.insert(stu_1);
db.students.insert(stu_2);
db.students.insert(stu_3);
db.students.insert(stu_4);
db.students.insert(stu_5);
db.students.insert(stu_6);




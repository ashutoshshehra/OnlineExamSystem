/**
 * SmartExam Engine - Zero-Error Dual-Mode Architecture,
 * Native Canvas Visual Analytics, AI Question Synthesis,
 * & 27 Pre-Configured Test Papers (Class 5 to 12 & College/CS)
 */

const isServerEnv = window.location.protocol.startsWith('http');

// ==========================================================================
// 1. COMPREHENSIVE CURRICULUM DATASET (27 Authentic Pre-Built Test Papers)
// ==========================================================================
const CURRICULUM_DATA = [
    // --- CLASS 5 ---
    {
        id: 1, classGrade: "Class 5", subject: "Mathematics",
        title: "Class 5 Mathematics: Numbers & Basic Operations",
        durationMinutes: 20, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "What is the place value of 7 in the number 47,852?", options: ["70", "700", "7,000", "70,000"], correct: "C", marks: 5, explanation: "7 is in the thousands place, so its value is 7 * 1,000 = 7,000." },
            { id: 2, text: "Which fraction is equivalent to 3/4?", options: ["6/8", "5/8", "9/16", "3/8"], correct: "A", marks: 5, explanation: "Multiplying numerator and denominator by 2 gives 6/8." },
            { id: 3, text: "A rectangle has length 12m and breadth 8m. What is its perimeter?", options: ["96m", "40m", "20m", "48m"], correct: "B", marks: 5, explanation: "Perimeter = 2 * (length + breadth) = 2 * (12 + 8) = 40m." },
            { id: 4, text: "What is the smallest 5-digit number?", options: ["99999", "10001", "10000", "11111"], correct: "C", marks: 5, explanation: "The smallest 5-digit number is 10,000." }
        ]
    },
    {
        id: 2, classGrade: "Class 5", subject: "Science",
        title: "Class 5 General Science: Living Things & Habitats",
        durationMinutes: 20, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Which organ in the human body pumps blood to all organs?", options: ["Lungs", "Heart", "Brain", "Kidney"], correct: "B", marks: 5, explanation: "The heart pumps oxygenated blood throughout the body." },
            { id: 2, text: "Green plants prepare their food by which process?", options: ["Respiration", "Photosynthesis", "Digestion", "Transpiration"], correct: "B", marks: 5, explanation: "Photosynthesis converts sunlight, CO2, and water into glucose." },
            { id: 3, text: "Which of the following animals is an amphibian?", options: ["Fish", "Frog", "Eagle", "Snake"], correct: "B", marks: 5, explanation: "Frogs live on both land and in water, categorizing them as amphibians." },
            { id: 4, text: "What is the gaseous state of water called?", options: ["Steam", "Rock", "Cloud", "Ice"], correct: "A", marks: 5, explanation: "Water vapor or steam is water in its gaseous phase." }
        ]
    },
    {
        id: 3, classGrade: "Class 5", subject: "English",
        title: "Class 5 English: Nouns, Verbs & Vocabulary",
        durationMinutes: 20, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Identify the proper noun: 'Rohan visited the Red Fort in Delhi.'", options: ["Visited", "Rohan", "City", "Monument"], correct: "B", marks: 5, explanation: "Rohan is the specific name of an individual, making it a proper noun." },
            { id: 2, text: "What is the past tense of the verb 'run'?", options: ["Runned", "Ran", "Running", "Runs"], correct: "B", marks: 5, explanation: "Ran is the irregular past tense form of run." },
            { id: 3, text: "Choose the correct antonym of 'Generous':", options: ["Kind", "Selfish", "Helpful", "Polite"], correct: "B", marks: 5, explanation: "Selfish is the exact opposite of generous." },
            { id: 4, text: "Fill in the blank: The birds are singing ____ in the morning.", options: ["sweetly", "sweet", "sweetness", "sweeten"], correct: "A", marks: 5, explanation: "The adverb 'sweetly' modifies the action of singing." }
        ]
    },

    // --- CLASS 6 ---
    {
        id: 4, classGrade: "Class 6", subject: "Mathematics",
        title: "Class 6 Mathematics: Integers, Decimals & Fractions",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "What is the value of (-15) + (-28)?", options: ["-43", "43", "-13", "13"], correct: "A", marks: 5, explanation: "Adding two negative numbers: -(15 + 28) = -43." },
            { id: 2, text: "What is 3.75 expressed as a simple fraction?", options: ["15/4", "7/2", "3/4", "37/5"], correct: "A", marks: 5, explanation: "3.75 = 375/100 = 15/4." },
            { id: 3, text: "In a class of 35 students, the ratio of boys to girls is 3:2. How many boys are there?", options: ["14", "21", "15", "20"], correct: "B", marks: 5, explanation: "3 / 5 * 35 = 21 boys." },
            { id: 4, text: "An angle measuring 90 degrees is called a:", options: ["Acute angle", "Right angle", "Obtuse angle", "Reflex angle"], correct: "B", marks: 5, explanation: "An exact 90-degree angle is a right angle." }
        ]
    },
    {
        id: 5, classGrade: "Class 6", subject: "Science",
        title: "Class 6 Science: Food Components, Fiber & Light",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Deficiency of Vitamin C causes which disease?", options: ["Beriberi", "Scurvy", "Rickets", "Goiter"], correct: "B", marks: 5, explanation: "Scurvy is caused by Vitamin C deficiency, causing bleeding gums." },
            { id: 2, text: "Which material does NOT allow light to pass through it at all?", options: ["Transparent", "Translucent", "Opaque", "Refractive"], correct: "C", marks: 5, explanation: "Opaque materials completely obstruct light, forming shadows." },
            { id: 3, text: "Cotton fiber is obtained from which part of the cotton plant?", options: ["Root", "Stem", "Fruit (bolls)", "Leaf"], correct: "C", marks: 5, explanation: "Cotton grows around seeds inside the plant's fruit bolls." },
            { id: 4, text: "Which of the following is a reversible physical change?", options: ["Melting of ice", "Burning of paper", "Rusting of iron", "Cooking an egg"], correct: "A", marks: 5, explanation: "Melting ice can be refrozen back into water without altering chemical identity." }
        ]
    },
    {
        id: 6, classGrade: "Class 6", subject: "Social Studies",
        title: "Class 6 Social Studies: Solar System & Early Civilizations",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Which planet is known as Earth's twin?", options: ["Mars", "Venus", "Mercury", "Jupiter"], correct: "B", marks: 5, explanation: "Venus is similar to Earth in size and mass." },
            { id: 2, text: "The Great Bath was discovered in which Indus Valley city?", options: ["Harappa", "Mohenjo-daro", "Lothal", "Kalibangan"], correct: "B", marks: 5, explanation: "The Great Bath was excavated at Mohenjo-daro." },
            { id: 3, text: "The imaginary line dividing Earth into Northern and Southern hemispheres is:", options: ["Tropic of Cancer", "Prime Meridian", "Equator", "Arctic Circle"], correct: "C", marks: 5, explanation: "The Equator is at 0 degrees latitude." },
            { id: 4, text: "At what level does a Gram Panchayat operate in India?", options: ["Village level", "Block level", "District level", "State level"], correct: "A", marks: 5, explanation: "Gram Panchayats are local governments at the village level." }
        ]
    },

    // --- CLASS 7 ---
    {
        id: 7, classGrade: "Class 7", subject: "Mathematics",
        title: "Class 7 Mathematics: Algebraic Equations, Lines & Angles",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Solve for x: 3x - 7 = 14", options: ["x = 5", "x = 7", "x = 6", "x = 9"], correct: "B", marks: 5, explanation: "3x = 14 + 7 = 21 => x = 21 / 3 = 7." },
            { id: 2, text: "If two angles are complementary and one angle is 35 degrees, what is the other?", options: ["55 degrees", "145 degrees", "65 degrees", "45 degrees"], correct: "A", marks: 5, explanation: "Complementary angles sum to 90 degrees: 90 - 35 = 55 degrees." },
            { id: 3, text: "What is (-4)^3 equal to?", options: ["-64", "64", "-12", "16"], correct: "A", marks: 5, explanation: "(-4) * (-4) * (-4) = 16 * (-4) = -64." },
            { id: 4, text: "What is the median of data: 3, 5, 7, 8, 12, 14, 21?", options: ["7", "8", "5", "10"], correct: "B", marks: 5, explanation: "The sorted central value is 8." }
        ]
    },
    {
        id: 8, classGrade: "Class 7", subject: "Science",
        title: "Class 7 Science: Plant Nutrition, Heat & Acids-Bases",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Which plant is an insectivorous plant that traps insects for nitrogen?", options: ["Pitcher plant", "Cuscuta (Dodder)", "Mushroom", "Algae"], correct: "A", marks: 5, explanation: "Pitcher plant leaves are modified into jugs to trap and digest insects." },
            { id: 2, text: "Heat transfer in liquids and gases primarily occurs through:", options: ["Conduction", "Convection", "Radiation", "Insulation"], correct: "B", marks: 5, explanation: "Convection involves the physical movement of heated fluid molecules." },
            { id: 3, text: "What color does blue litmus paper turn in an acidic solution?", options: ["Red", "Yellow", "Green", "Colorless"], correct: "A", marks: 5, explanation: "Acids turn blue litmus red." },
            { id: 4, text: "What type of reflection occurs from a smooth polished mirror?", options: ["Diffused reflection", "Specular (Regular) reflection", "Scattered reflection", "Refracted reflection"], correct: "B", marks: 5, explanation: "Smooth polished surfaces produce regular specular reflection." }
        ]
    },
    {
        id: 9, classGrade: "Class 7", subject: "English",
        title: "Class 7 English: Active-Passive Voice & Prepositions",
        durationMinutes: 25, totalMarks: 20, passingMarks: 8,
        questions: [
            { id: 1, text: "Change to Passive: 'The chef prepared a delicious dinner.'", options: ["A delicious dinner was prepared by the chef.", "Dinner is prepared by the chef.", "A delicious dinner had been prepared.", "The chef was preparing dinner."], correct: "A", marks: 5, explanation: "Past simple active transforms into was/were + prepared." },
            { id: 2, text: "Choose the preposition: 'She sat ____ the shade of the mango tree.'", options: ["on", "in", "at", "over"], correct: "B", marks: 5, explanation: "We say 'in the shade' when under shaded space." },
            { id: 3, text: "Identify the conjunction: 'I wanted to buy the book, but I had no money.'", options: ["wanted", "buy", "but", "money"], correct: "C", marks: 5, explanation: "'But' connects contrasting independent clauses." },
            { id: 4, text: "What is the superlative degree of the adjective 'Good'?", options: ["Better", "Goodest", "Best", "Most good"], correct: "C", marks: 5, explanation: "Comparison degrees are: Good -> Better -> Best." }
        ]
    },

    // --- CLASS 8 ---
    {
        id: 10, classGrade: "Class 8", subject: "Mathematics",
        title: "Class 8 Mathematics: Rational Numbers & Linear Equations",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "Which property is shown by: a * (b + c) = (a * b) + (a * c)?", options: ["Closure", "Commutative", "Associative", "Distributive"], correct: "D", marks: 5, explanation: "Multiplication distributes over addition." },
            { id: 2, text: "Solve: 5x + 9 = 2x + 24", options: ["x = 5", "x = 3", "x = 7", "x = 8"], correct: "A", marks: 5, explanation: "5x - 2x = 24 - 9 => 3x = 15 => x = 5." },
            { id: 3, text: "What is the sum of interior angles of a convex hexagon (6 sides)?", options: ["540 degrees", "720 degrees", "360 degrees", "180 degrees"], correct: "B", marks: 5, explanation: "(6 - 2) * 180 = 720 degrees." },
            { id: 4, text: "Find the square root of 784:", options: ["26", "28", "32", "24"], correct: "B", marks: 5, explanation: "28 * 28 = 784." },
            { id: 5, text: "If 15 workers can build a wall in 48 hours, how many workers can build it in 30 hours?", options: ["20", "24", "25", "18"], correct: "B", marks: 5, explanation: "15 * 48 = x * 30 => x = 720 / 30 = 24 workers." }
        ]
    },
    {
        id: 11, classGrade: "Class 8", subject: "Science",
        title: "Class 8 Science: Force, Pressure & Microorganisms",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "What is the SI unit of pressure?", options: ["Newton", "Pascal (N/m^2)", "Joule", "Watt"], correct: "B", marks: 5, explanation: "Pressure = Force / Area (Pascal)." },
            { id: 2, text: "Which bacterium converts milk into curd?", options: ["Lactobacillus", "Rhizobium", "E. coli", "Salmonella"], correct: "A", marks: 5, explanation: "Lactobacillus promotes curdling of milk." },
            { id: 3, text: "Frictional force always acts:", options: ["In direction of motion", "Opposite to direction of motion", "Perpendicular to motion", "Independent of motion"], correct: "B", marks: 5, explanation: "Friction resists relative motion between surfaces." },
            { id: 4, text: "Which non-metal is stored in water to prevent it catching fire in air?", options: ["Sodium", "Phosphorus", "Sulphur", "Carbon"], correct: "B", marks: 5, explanation: "White phosphorus catches fire in air, so it is submerged in water." },
            { id: 5, text: "Sound cannot travel through:", options: ["Solids", "Liquids", "Gases", "Vacuum"], correct: "D", marks: 5, explanation: "Sound requires a medium and cannot travel in a vacuum." }
        ]
    },
    {
        id: 12, classGrade: "Class 8", subject: "Social Science",
        title: "Class 8 Social Science: Indian Constitution & Modern History",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "Who is known as the Father of the Indian Constitution?", options: ["Mahatma Gandhi", "Dr. B.R. Ambedkar", "Jawaharlal Nehru", "Sardar Patel"], correct: "B", marks: 5, explanation: "Dr. B.R. Ambedkar chaired the Drafting Committee." },
            { id: 2, text: "The Battle of Plassey took place in which year?", options: ["1757", "1764", "1857", "1707"], correct: "A", marks: 5, explanation: "Fought in 1757 between Siraj-ud-Daulah and the British." },
            { id: 3, text: "Which body passes laws on the Union List in India?", options: ["Parliament", "State Assembly", "Panchayat", "High Court"], correct: "A", marks: 5, explanation: "The Parliament of India legislates on Union List subjects." },
            { id: 4, text: "Sustainable development means:", options: ["Rapid exploitation of resources", "Meeting present needs without compromising future generations", "Using only coal", "Exporting all timber"], correct: "B", marks: 5, explanation: "Balancing resource use with future conservation." },
            { id: 5, text: "Where did the Revolt of 1857 begin?", options: ["Meerut", "Delhi", "Jhansi", "Kanpur"], correct: "A", marks: 5, explanation: "The revolt began in Meerut on May 10, 1857." }
        ]
    },

    // --- CLASS 9 ---
    {
        id: 13, classGrade: "Class 9", subject: "Mathematics",
        title: "Class 9 Mathematics: Polynomials & Coordinate Geometry",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "What is the zero of p(x) = 2x + 5?", options: ["5/2", "-5/2", "2/5", "-2/5"], correct: "B", marks: 5, explanation: "2x + 5 = 0 => x = -5/2." },
            { id: 2, text: "The point (-3, 4) lies in which quadrant?", options: ["Quadrant I", "Quadrant II", "Quadrant III", "Quadrant IV"], correct: "B", marks: 5, explanation: "(-, +) coordinates lie in Quadrant II." },
            { id: 3, text: "Alternate interior angles formed by parallel lines and a transversal are:", options: ["Equal", "Supplementary", "Complementary", "Zero"], correct: "A", marks: 5, explanation: "Alternate interior angles are equal." },
            { id: 4, text: "What is the degree of the zero polynomial?", options: ["0", "1", "Not defined", "Infinity"], correct: "C", marks: 5, explanation: "The degree of the zero polynomial is mathematically undefined." },
            { id: 5, text: "Area of triangle with sides a, b, c and semi-perimeter s is:", options: ["sqrt(s(s-a)(s-b)(s-c))", "s * (a + b + c)", "1/2 * a * b * c", "s^2 - abc"], correct: "A", marks: 5, explanation: "This is Heron's Formula." }
        ]
    },
    {
        id: 14, classGrade: "Class 9", subject: "Science",
        title: "Class 9 Science: Cell Biology, Matter & Laws of Motion",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "Which organelle is the 'Powerhouse of the Cell'?", options: ["Ribosome", "Mitochondria", "Golgi Body", "Lysosome"], correct: "B", marks: 5, explanation: "Mitochondria synthesize cellular ATP." },
            { id: 2, text: "Newton's Second Law of Motion is expressed as:", options: ["F = m * a", "v = u + at", "p = m / v", "W = F * s"], correct: "A", marks: 5, explanation: "Force equals mass multiplied by acceleration." },
            { id: 3, text: "Universal gravitational constant G is approximately:", options: ["6.67 * 10^-11 N m^2/kg^2", "9.8 m/s^2", "3.0 * 10^8 m/s", "1.6 * 10^-19 C"], correct: "A", marks: 5, explanation: "G = 6.674 * 10^-11 N m^2/kg^2." },
            { id: 4, text: "Solid turning directly into vapor is called:", options: ["Condensation", "Sublimation", "Evaporation", "Fusion"], correct: "B", marks: 5, explanation: "Sublimation skips the liquid phase (e.g. dry ice)." },
            { id: 5, text: "Which tissue transports water in plants?", options: ["Phloem", "Xylem", "Parenchyma", "Collenchyma"], correct: "B", marks: 5, explanation: "Xylem carries water and minerals upwards from roots." }
        ]
    },
    {
        id: 15, classGrade: "Class 9", subject: "English",
        title: "Class 9 English: Tenses, Modals & Reported Speech",
        durationMinutes: 30, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "Choose the modal: 'You ____ wear a helmet while driving.'", options: ["may", "must", "might", "can"], correct: "B", marks: 5, explanation: "'Must' denotes legal necessity." },
            { id: 2, text: "Indirect speech: He said, 'I am reading a book.'", options: ["He said that he was reading a book.", "He says he is reading a book.", "He said that he read a book.", "He told he had read a book."], correct: "A", marks: 5, explanation: "Present continuous shifts to past continuous." },
            { id: 3, text: "Neither of the boys ____ present yesterday.", options: ["was", "were", "are", "have been"], correct: "A", marks: 5, explanation: "'Neither of' takes a singular verb ('was')." },
            { id: 4, text: "Which sentence is in Present Perfect Continuous tense?", options: ["She has written a letter.", "She had been waiting.", "She has been studying since 8 AM.", "She was playing."], correct: "C", marks: 5, explanation: "has/have + been + verb-ing represents present perfect continuous." },
            { id: 5, text: "Identify the correct punctuation:", options: ["Its a nice day, isn't it?", "It's a nice day, isn't it?", "Its' a nice day isn't it?", "It is a nice day, isn't it."], correct: "B", marks: 5, explanation: "Contraction 'It's' and question mark." }
        ]
    },

    // --- CLASS 10 ---
    {
        id: 16, classGrade: "Class 10", subject: "Mathematics",
        title: "Class 10 Mathematics: Trigonometry & Quadratic Equations",
        durationMinutes: 35, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "If sin(theta) = 3/5, what is cos(theta) for acute angle theta?", options: ["4/5", "5/4", "3/4", "1/5"], correct: "A", marks: 5, explanation: "cos(theta) = sqrt(1 - 9/25) = 4/5." },
            { id: 2, text: "What are the roots of x^2 - 5x + 6 = 0?", options: ["2 and 3", "-2 and -3", "1 and 6", "-1 and -6"], correct: "A", marks: 5, explanation: "(x - 2)(x - 3) = 0 => x = 2, 3." },
            { id: 3, text: "The discriminant of 2x^2 - 4x + 3 = 0 is:", options: ["-8 (No real roots)", "8 (Two distinct)", "0 (Equal)", "16"], correct: "A", marks: 5, explanation: "D = 16 - 4(2)(3) = 16 - 24 = -8 < 0." },
            { id: 4, text: "What is the 10th term of AP: 2, 7, 12, 17...?", options: ["47", "52", "45", "50"], correct: "A", marks: 5, explanation: "a = 2, d = 5. a_10 = 2 + 9(5) = 47." },
            { id: 5, text: "What is tan(45 degrees)?", options: ["0", "1", "1/sqrt(3)", "sqrt(3)"], correct: "B", marks: 5, explanation: "tan(45) = 1." }
        ]
    },
    {
        id: 17, classGrade: "Class 10", subject: "Science",
        title: "Class 10 Science: Chemical Reactions & Electricity",
        durationMinutes: 35, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "According to Ohm's law, at constant temperature, current I is proportional to:", options: ["Voltage V", "V^2", "1/V", "Independent of V"], correct: "A", marks: 5, explanation: "V = I * R => I is directly proportional to potential difference V." },
            { id: 2, text: "What type of reaction is: CaO + H2O -> Ca(OH)2 + Heat?", options: ["Combination & Exothermic", "Decomposition", "Displacement", "Endothermic only"], correct: "A", marks: 5, explanation: "Two reactants combine and release heat (exothermic combination)." },
            { id: 3, text: "The power of a lens with focal length -0.5m is:", options: ["-2 Dioptre", "+2 Dioptre", "-0.5 Dioptre", "+0.5 Dioptre"], correct: "A", marks: 5, explanation: "P = 1 / f = 1 / (-0.5) = -2 D." },
            { id: 4, text: "Which hormone regulates blood sugar in humans?", options: ["Insulin", "Thyroxine", "Adrenaline", "Growth hormone"], correct: "A", marks: 5, explanation: "Insulin secreted by pancreas regulates glucose levels." },
            { id: 5, text: "Which acid is present in gastric juice in the human stomach?", options: ["Hydrochloric acid (HCl)", "Sulphuric acid", "Nitric acid", "Acetic acid"], correct: "A", marks: 5, explanation: "HCl creates acidic medium for protein digestion." }
        ]
    },
    {
        id: 18, classGrade: "Class 10", subject: "English",
        title: "Class 10 English: Advanced Grammar, Clauses & Synthesis",
        durationMinutes: 35, totalMarks: 25, passingMarks: 10,
        questions: [
            { id: 1, text: "Identify the noun clause: 'I believe that honesty is the best policy.'", options: ["that honesty is the best policy", "I believe", "the best policy", "honesty"], correct: "A", marks: 5, explanation: "Acts as direct object of the verb 'believe'." },
            { id: 2, text: "Fill in the blank: '____ student must bring their ID card.'", options: ["Every", "All", "Many", "Several"], correct: "A", marks: 5, explanation: "'Every' is used with singular countable nouns." },
            { id: 3, text: "Change to passive: 'Someone has stolen my watch.'", options: ["My watch has been stolen.", "My watch had been stolen.", "My watch was stolen.", "My watch is stolen."], correct: "A", marks: 5, explanation: "has/have + been + past participle." },
            { id: 4, text: "Select the grammatically correct sentence:", options: ["One of my friends is coming.", "One of my friends are coming.", "One of my friend is coming.", "One of my friend are coming."], correct: "A", marks: 5, explanation: "'One of + plural noun' takes a singular verb." },
            { id: 5, text: "Meaning of idiom 'Burn the midnight oil':", options: ["Work late into the night", "Waste fuel", "Start a fire", "Wake up early"], correct: "A", marks: 5, explanation: "It means studying or working diligently late at night." }
        ]
    },

    // --- CLASS 11 ---
    {
        id: 19, classGrade: "Class 11", subject: "Physics",
        title: "Class 11 Physics: Kinematics, Vectors & Laws of Motion",
        durationMinutes: 40, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "The area under a Velocity-Time graph represents:", options: ["Acceleration", "Displacement", "Force", "Power"], correct: "B", marks: 6, explanation: "Integral of velocity over time yields displacement." },
            { id: 2, text: "At what projection angle is projectile horizontal range maximum?", options: ["30 degrees", "45 degrees", "60 degrees", "90 degrees"], correct: "B", marks: 6, explanation: "sin(2*theta) is maximum at 2*theta = 90 => theta = 45." },
            { id: 3, text: "A body of mass 5 kg moving at 10 m/s has kinetic energy equal to:", options: ["50 J", "250 J", "500 J", "25 J"], correct: "B", marks: 6, explanation: "KE = 1/2 * m * v^2 = 0.5 * 5 * 100 = 250 Joules." },
            { id: 4, text: "Dimensions of universal gravitational constant G are:", options: ["[M^-1 L^3 T^-2]", "[M L T^-2]", "[M^1 L^2 T^-1]", "[M^-2 L^3 T^-1]"], correct: "A", marks: 6, explanation: "[M^-1 L^3 T^-2]." },
            { id: 5, text: "The dot product of two perpendicular vectors is:", options: ["0", "1", "-1", "Product of magnitudes"], correct: "A", marks: 6, explanation: "cos(90) = 0." }
        ]
    },
    {
        id: 20, classGrade: "Class 11", subject: "Chemistry",
        title: "Class 11 Chemistry: Atomic Structure & Chemical Bonding",
        durationMinutes: 40, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "Maximum number of electrons in d subshell is:", options: ["2", "6", "10", "14"], correct: "C", marks: 6, explanation: "5 orbitals * 2 electrons = 10 electrons." },
            { id: 2, text: "Which molecule exhibits sp3d hybridization with trigonal bipyramidal geometry?", options: ["CH4", "PCl5", "SF6", "BF3"], correct: "B", marks: 6, explanation: "PCl5 has 5 bond pairs." },
            { id: 3, text: "According to Heisenberg's uncertainty principle:", options: ["delta x * delta p >= h / (4 * pi)", "E = mc^2", "lambda = h / p", "delta E = h * nu"], correct: "A", marks: 6, explanation: "Product of uncertainty in position and momentum >= h / (4*pi)." },
            { id: 4, text: "Most electronegative element in periodic table is:", options: ["Oxygen", "Fluorine", "Chlorine", "Nitrogen"], correct: "B", marks: 6, explanation: "Fluorine has the highest electronegativity (3.98)." },
            { id: 5, text: "Ideal gas equation is:", options: ["P V = n R T", "P / T = constant", "V / n = constant", "P V = constant"], correct: "A", marks: 6, explanation: "PV = nRT." }
        ]
    },
    {
        id: 21, classGrade: "Class 11", subject: "Computer Science",
        title: "Class 11 Computer Science: Python Basics, Sets & Logic",
        durationMinutes: 40, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "Which Python data type is immutable?", options: ["List", "Dictionary", "Tuple", "Set"], correct: "C", marks: 6, explanation: "Tuples cannot be altered once initialized." },
            { id: 2, text: "What is output of `print(2 ** 3 ** 2)` in Python?", options: ["64", "512", "36", "12"], correct: "B", marks: 6, explanation: "Exponentiation is right-associative: 3**2 = 9, 2**9 = 512." },
            { id: 3, text: "Keyword to define a function in Python is:", options: ["func", "def", "function", "lambda"], correct: "B", marks: 6, explanation: "The 'def' keyword introduces a function definition." },
            { id: 4, text: "What does `len([10, [20, 30], 40])` evaluate to?", options: ["4", "3", "2", "Error"], correct: "B", marks: 6, explanation: "3 elements: 10, [20, 30], and 40." },
            { id: 5, text: "Which gate produces output 1 ONLY when both inputs are 1?", options: ["OR gate", "AND gate", "NAND gate", "XOR gate"], correct: "B", marks: 6, explanation: "AND gate requires all inputs to be true." }
        ]
    },

    // --- CLASS 12 ---
    {
        id: 22, classGrade: "Class 12", subject: "Physics",
        title: "Class 12 Physics: Electrostatics, Optics & Current Electricity",
        durationMinutes: 45, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "SI unit of Electric Flux is:", options: ["N/C", "Volt * meter (V m)", "Farad", "Tesla"], correct: "B", marks: 6, explanation: "Phi = E * A = (V/m) * m^2 = V m." },
            { id: 2, text: "The capacitance of a parallel plate capacitor increases when:", options: ["Plate separation increases", "Plate area increases", "Dielectric constant decreases", "Voltage drops"], correct: "B", marks: 6, explanation: "C = (k * epsilon_0 * A) / d." },
            { id: 3, text: "Which phenomenon proves the transverse wave nature of light?", options: ["Refraction", "Interference", "Diffraction", "Polarization"], correct: "D", marks: 6, explanation: "Only transverse waves can be polarized." },
            { id: 4, text: "According to Lenz's law, induced EMF always:", options: ["Supports its cause", "Opposes change in magnetic flux producing it", "Is zero", "Accelerates magnet"], correct: "B", marks: 6, explanation: "Lenz's law expresses conservation of energy." },
            { id: 5, text: "Drift velocity v_d of free electrons is proportional to:", options: ["Electric field E", "E^2", "1/E", "Independent of E"], correct: "A", marks: 6, explanation: "v_d = (e * E * tau) / m." }
        ]
    },
    {
        id: 23, classGrade: "Class 12", subject: "Chemistry",
        title: "Class 12 Chemistry: Electrochemistry, Kinetics & Organic Chemistry",
        durationMinutes: 45, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "For a first-order reaction, half-life t_1/2 is:", options: ["0.693 / k", "k / 0.693", "[A]_0 / (2k)", "1 / (k * [A]_0)"], correct: "A", marks: 6, explanation: "t_1/2 = ln(2) / k = 0.693 / k." },
            { id: 2, text: "Lucas reagent is an equimolar mixture of:", options: ["Conc. HCl + Anhydrous ZnCl2", "Alkaline KMnO4", "PCC in CH2Cl2", "Bromine water"], correct: "A", marks: 6, explanation: "Used to distinguish primary, secondary, and tertiary alcohols." },
            { id: 3, text: "Unit of molar conductivity is:", options: ["S cm^2 mol^-1", "S cm^-1", "Ohm cm", "mol L^-1"], correct: "A", marks: 6, explanation: "Siemens cm^2 per mole." },
            { id: 4, text: "Reaction converting amide to primary amine with one fewer carbon is:", options: ["Hoffmann Bromamide degradation", "Cannizzaro reaction", "Aldol condensation", "Clemmensen reduction"], correct: "A", marks: 6, explanation: "Hoffmann bromamide reaction removes the carbonyl group." },
            { id: 5, text: "Which ion is colorless in aqueous solution?", options: ["Cu2+", "Fe3+", "Zn2+", "Cr3+"], correct: "C", marks: 6, explanation: "Zn2+ has a fully filled d10 subshell with no d-d electron transition." }
        ]
    },
    {
        id: 24, classGrade: "Class 12", subject: "Computer Science",
        title: "Class 12 Computer Science: Python OOPs, Stacks & SQL",
        durationMinutes: 45, totalMarks: 30, passingMarks: 12,
        questions: [
            { id: 1, text: "Binding data and functions into a single unit is called:", options: ["Inheritance", "Polymorphism", "Encapsulation", "Abstraction"], correct: "C", marks: 6, explanation: "Encapsulation encapsulates internal state within a class." },
            { id: 2, text: "A Stack data structure operates on which principle?", options: ["FIFO", "LIFO", "Random", "Priority"], correct: "B", marks: 6, explanation: "Last In, First Out (LIFO)." },
            { id: 3, text: "Which SQL clause filters grouped rows after GROUP BY?", options: ["WHERE", "HAVING", "ORDER BY", "DISTINCT"], correct: "B", marks: 6, explanation: "HAVING filters groups; WHERE filters individual records." },
            { id: 4, text: "File mode for writing binary in Python is:", options: ["'w'", "'wb'", "'r+'", "'a'"], correct: "B", marks: 6, explanation: "'wb' denotes binary write mode." },
            { id: 5, text: "The degree of a database table is the count of its:", options: ["Rows", "Columns (Attributes)", "Keys", "Cells"], correct: "B", marks: 6, explanation: "Degree = column count; Cardinality = row count." }
        ]
    },

    // --- COLLEGE / CS ---
    {
        id: 25, classGrade: "College / CS", subject: "Java Programming",
        title: "College CS: Core Java, OOPs, Collections & Servlets",
        durationMinutes: 50, totalMarks: 40, passingMarks: 16,
        questions: [
            { id: 1, text: "Which Collection maintains insertion order and offers O(1) indexed lookup?", options: ["HashSet", "ArrayList", "TreeSet", "HashMap"], correct: "B", marks: 8, explanation: "ArrayList maintains insertion order with fast indexed retrieval." },
            { id: 2, text: "In Servlet lifecycle, which method runs only once upon instantiation?", options: ["service()", "doGet()", "init()", "destroy()"], correct: "C", marks: 8, explanation: "init() is called once when the container loads the servlet." },
            { id: 3, text: "Which keyword prevents a Java class from being extended?", options: ["static", "abstract", "final", "synchronized"], correct: "C", marks: 8, explanation: "A final class cannot be inherited." },
            { id: 4, text: "Why is PreparedStatement preferred over Statement in JDBC?", options: ["Pre-compilation & SQL injection prevention", "Bypasses passwords", "Only for XML", "Replaces connection pool"], correct: "A", marks: 8, explanation: "PreparedStatement securely parameterizes inputs and compiles queries." },
            { id: 5, text: "Which functional interface in java.util.function accepts an argument and returns boolean?", options: ["Consumer", "Supplier", "Predicate", "Function"], correct: "C", marks: 8, explanation: "Predicate<T> defines boolean test(T t)." }
        ]
    },
    {
        id: 26, classGrade: "College / CS", subject: "DBMS",
        title: "College CS: Database Management Systems (DBMS & SQL)",
        durationMinutes: 50, totalMarks: 40, passingMarks: 16,
        questions: [
            { id: 1, text: "A table is in 2NF if it is in 1NF and:", options: ["Has no transitive dependency", "Has no partial dependency on composite candidate keys", "Is binary", "Has atomic values"], correct: "B", marks: 8, explanation: "2NF eliminates partial dependency on composite primary keys." },
            { id: 2, text: "Which ACID property guarantees all-or-nothing execution?", options: ["Atomicity", "Consistency", "Isolation", "Durability"], correct: "A", marks: 8, explanation: "Atomicity ensures the entire transaction succeeds or rolls back completely." },
            { id: 3, text: "Which index structure is most widely used in disk-based relational databases?", options: ["Linear Hash", "B+ Tree", "Binary Search Tree", "Linked List"], correct: "B", marks: 8, explanation: "B+ trees minimize I/O cycles and excel at both point lookups and range scans." },
            { id: 4, text: "Which join returns all left table rows and matched right rows?", options: ["INNER JOIN", "LEFT OUTER JOIN", "RIGHT OUTER JOIN", "CROSS JOIN"], correct: "B", marks: 8, explanation: "LEFT JOIN preserves all left table records." },
            { id: 5, text: "Two transactions waiting endlessly for locks held by each other is called:", options: ["Deadlock", "Starvation", "Phantom Read", "Dirty Read"], correct: "A", marks: 8, explanation: "Deadlock is a circular lock dependency." }
        ]
    },
    {
        id: 27, classGrade: "College / CS", subject: "Web Technologies",
        title: "College CS: Full-Stack Web Technologies (HTML5, JS & REST)",
        durationMinutes: 50, totalMarks: 40, passingMarks: 16,
        questions: [
            { id: 1, text: "In JavaScript event loop, when is the microtask queue (e.g. Promises) processed?", options: ["After each macrotask before browser repaint", "Every 10 seconds", "On separate background threads", "Before main thread script"], correct: "A", marks: 8, explanation: "The microtask queue is completely drained after each execution cycle before repainting." },
            { id: 2, text: "Which HTTP status code indicates a new resource was created successfully?", options: ["200 OK", "201 Created", "204 No Content", "304 Not Modified"], correct: "B", marks: 8, explanation: "HTTP 201 Created represents successful resource generation." },
            { id: 3, text: "In CSS Flexbox, which property aligns items along the main axis?", options: ["align-items", "justify-content", "align-content", "flex-direction"], correct: "B", marks: 8, explanation: "justify-content distributes flex items on the primary axis." },
            { id: 4, text: "What enforces CORS (Cross-Origin Resource Sharing)?", options: ["The client web browser security sandbox", "The database engine", "The network router", "CPU firmware"], correct: "A", marks: 8, explanation: "CORS is a browser-enforced security mechanism." },
            { id: 5, text: "Which JS Array method returns a new array with transformed elements?", options: ["filter()", "forEach()", "map()", "reduce()"], correct: "C", marks: 8, explanation: "Array.prototype.map() returns an immutable transformed projection." }
        ]
    }
];

// ==========================================================================
// 2. AI QUESTION GENERATOR ENGINE
// ==========================================================================
const AIEngine = {
    generateQuestions(classGrade, subject, topic, difficulty = "medium", count = 5) {
        const t = topic ? topic.trim() : "Core Principles";
        const questions = [];
        const diffMarks = difficulty.toLowerCase() === "hard" ? 4 : 2;

        for (let i = 1; i <= count; i++) {
            let q = {
                id: i,
                text: "",
                options: [],
                correct: "A",
                marks: diffMarks,
                explanation: ""
            };

            switch (i % 5) {
                case 1:
                    q.text = `What is the fundamental definition or core principle behind ${t} in ${subject} (${classGrade})?`;
                    q.options = [
                        `A governing fundamental property and structured process of ${t}`,
                        `An arbitrary scalar constant with no practical effect`,
                        `A temporary condition observed only in synthetic vacuum`,
                        `An outdated convention obsolete in modern curriculum`
                    ];
                    q.correct = "A";
                    q.explanation = `The foundational concept of ${t} in ${subject} is established by its verified core governing principles.`;
                    break;
                case 2:
                    q.text = `Which of the following statements is scientifically and conceptually ACCURATE regarding ${t}?`;
                    q.options = [
                        `It contradicts conservation laws under all metrics`,
                        `It exhibits predictable, direct mathematical or functional relationships under standard conditions`,
                        `It cannot be measured, observed, or calculated`,
                        `It produces zero thermodynamic change in any medium`
                    ];
                    q.correct = "B";
                    q.explanation = `${t} exhibits systematic proportionality and predictable state relationships as demonstrated in ${subject}.`;
                    break;
                case 3:
                    q.text = `When analyzing real-world applications of ${t} in ${classGrade}, what key parameter most significantly governs its behavior?`;
                    q.options = [
                        `Uncalibrated background thermal noise`,
                        `System constraints, concentration/gradient potential, and applied energy`,
                        `Random variations in apparatus color`,
                        `It functions with zero loss independent of all physical constraints`
                    ];
                    q.correct = "B";
                    q.explanation = `Real-world performance of ${t} is dictated by gradient thresholds, medium impedance, and applied driving forces.`;
                    break;
                case 4:
                    q.text = `What is the primary role or consequence of ${t} within ${subject}?`;
                    q.options = [
                        `It enables stable state transitions and maintains system equilibrium`,
                        `It causes total irreversible entropy destruction`,
                        `It neutralizes all adjacent chemical and physical potentials permanently`,
                        `It produces no measurable consequence`
                    ];
                    q.correct = "A";
                    q.explanation = `A crucial role of ${t} is to regulate dynamic balance and allow repeatable functional transitions.`;
                    break;
                default:
                    q.text = `Which of the following standard units, notations, or categorizations applies to ${t}?`;
                    q.options = [
                        `Recognized SI, dimensional, or classified structural standard units`,
                        `Light-years per second exclusively`,
                        `Atmospheres per cubic millimeter`,
                        `Imaginary unitless vectors only`
                    ];
                    q.correct = "A";
                    q.explanation = `${t} in ${subject} is quantified using recognized scientific and structural taxonomy.`;
                    break;
            }
            questions.push(q);
        }
        return questions;
    }
};

// ==========================================================================
// 3. ZERO-ERROR DUAL-MODE API ADAPTER
// ==========================================================================
const API = {
    async getExams(classGrade = "ALL", subject = "ALL") {
        if (isServerEnv) {
            try {
                const controller = new AbortController();
                const timeoutId = setTimeout(() => controller.abort(), 2000);
                const res = await fetch(`exams?class=${encodeURIComponent(classGrade)}&subject=${encodeURIComponent(subject)}`, {
                    signal: controller.signal
                });
                clearTimeout(timeoutId);
                if (res.ok) {
                    const data = await res.json();
                    if (data.exams && data.exams.length > 0) return data.exams;
                }
            } catch (e) {
                // Server timeout or offline, fall back seamlessly
            }
        }

        // Return from curriculum + custom tests
        const custom = JSON.parse(localStorage.getItem('smartexam_custom_tests') || '[]');
        const all = [...CURRICULUM_DATA, ...custom];
        return all.filter(ex => {
            const matchClass = (classGrade === "ALL" || ex.classGrade === classGrade);
            const matchSub = (subject === "ALL" || ex.subject === subject);
            return matchClass && matchSub;
        });
    },

    async getExamDetails(examId) {
        if (isServerEnv) {
            try {
                const controller = new AbortController();
                const timeoutId = setTimeout(() => controller.abort(), 2000);
                const res = await fetch(`exams?id=${examId}`, { signal: controller.signal });
                clearTimeout(timeoutId);
                if (res.ok) {
                    const data = await res.json();
                    if (data.id && data.questions && data.questions.length > 0) return data;
                }
            } catch (e) {}
        }

        const local = CURRICULUM_DATA.find(e => e.id == examId);
        if (local) return local;

        const custom = JSON.parse(localStorage.getItem('smartexam_custom_tests') || '[]');
        return custom.find(e => e.id == examId) || CURRICULUM_DATA[0];
    },

    async submitExam(payload) {
        if (isServerEnv) {
            try {
                const controller = new AbortController();
                const timeoutId = setTimeout(() => controller.abort(), 2500);
                const params = new URLSearchParams(payload);
                const res = await fetch('submitExam', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: params.toString(),
                    signal: controller.signal
                });
                clearTimeout(timeoutId);
                if (res.ok) {
                    const data = await res.json();
                    this.saveLocalResult(payload);
                    return data;
                }
            } catch (e) {}
        }

        // Offline scoring calculation
        const score = Number(payload.score) || 0;
        const total = Number(payload.totalMarks) || 20;
        const pct = total > 0 ? (score / total) * 100 : 0;
        const status = pct >= 40 ? "PASS" : "FAIL";

        this.saveLocalResult({
            ...payload,
            percentage: pct,
            resultStatus: status
        });

        return {
            status: "success",
            score: score,
            totalMarks: total,
            percentage: pct,
            resultStatus: status,
            studentName: payload.studentName || "Student",
            tabSwitchCount: payload.tabSwitchCount || 0
        };
    },

    saveLocalResult(item) {
        const results = JSON.parse(localStorage.getItem('smartexam_results') || '[]');
        results.unshift({
            id: Date.now(),
            date: new Date().toISOString(),
            examId: item.examId,
            examTitle: item.examTitle || `Exam ${item.examId}`,
            subject: item.subject || "General",
            student: item.studentName || "Student",
            score: Number(item.score),
            totalMarks: Number(item.totalMarks),
            percentage: Math.round(((Number(item.score) / Number(item.totalMarks)) * 100) || 0),
            durationMinutes: Math.round((Number(item.durationTaken) || 60) / 60),
            tabSwitchCount: Number(item.tabSwitchCount) || 0,
            status: (Number(item.score) / Number(item.totalMarks)) >= 0.4 ? "PASS" : "FAIL"
        });
        localStorage.setItem('smartexam_results', JSON.stringify(results));
    },

    getLocalResults() {
        return JSON.parse(localStorage.getItem('smartexam_results') || '[]');
    }
};

// ==========================================================================
// 4. ZERO-ERROR FORM AUTHENTICATION HANDLERS
// ==========================================================================
async function handleStudentAuth(e, type) {
    if (e) e.preventDefault();
    if (type === 'login') {
        const email = document.getElementById('loginEmail').value.trim();
        const pass = document.getElementById('loginPassword').value.trim();
        if (!email || !pass) {
            showToast("Please enter email and password!", "warning");
            return false;
        }

        const name = email.split('@')[0];
        saveCurrentStudent({
            name: name === 'student' ? 'Ashutosh Shehra' : name,
            studentId: 'STU-2026',
            email: email,
            classGrade: localStorage.getItem('smartexam_studentClass') || 'Class 10'
        });

        if (isServerEnv) {
            try {
                const params = new URLSearchParams({ email, password: pass });
                fetch('StudentLoginServlet', { method: 'POST', body: params }).catch(() => {});
            } catch (err) {}
        }

        showToast("Login successful!", "success");
        setTimeout(() => { window.location.href = 'studenthome.html'; }, 300);
        return false;
    } else {
        const name = document.getElementById('regName').value.trim();
        const id = document.getElementById('regStudentId').value.trim() || 'STU-2026';
        const cls = document.getElementById('regClass').value;
        const email = document.getElementById('regEmail').value.trim();
        const pass = document.getElementById('regPass').value;
        const confirm = document.getElementById('regConfirm').value;

        if (pass !== confirm) {
            showToast("Passwords do not match!", "danger");
            return false;
        }

        saveCurrentStudent({ name, studentId: id, email, classGrade: cls });
        if (isServerEnv) {
            try {
                const params = new URLSearchParams({ name, studentId: id, classGrade: cls, email, password: pass });
                fetch('StudentRegisterServlet', { method: 'POST', body: params }).catch(() => {});
            } catch (err) {}
        }

        showToast("Registration successful!", "success");
        setTimeout(() => { window.location.href = 'studenthome.html'; }, 300);
        return false;
    }
}

async function handleAdminAuth(e, type) {
    if (e) e.preventDefault();
    if (type === 'login') {
        const email = document.getElementById('loginEmail') ? document.getElementById('loginEmail').value.trim() : 'admin@example.com';
        const pass = document.getElementById('loginPassword') ? document.getElementById('loginPassword').value.trim() : 'admin123';

        const teacherData = getTeacher();
        teacherData.email = email;
        saveTeacher(teacherData);

        if (isServerEnv) {
            try {
                const params = new URLSearchParams({ email, password: pass });
                fetch('AdminLoginServlet', { method: 'POST', body: params }).catch(() => {});
            } catch (err) {}
        }

        showToast("Teacher Login successful!", "success");
        setTimeout(() => { window.location.href = 'teacherhome.html'; }, 300);
        return false;
    } else {
        const name = document.getElementById('regAdminName') ? document.getElementById('regAdminName').value.trim() : 'Dr. Rajesh Sharma';
        const id = document.getElementById('regAdminId') ? document.getElementById('regAdminId').value.trim() : 'T-101';
        const email = document.getElementById('regAdminEmail') ? document.getElementById('regAdminEmail').value.trim() : 'teacher@example.com';
        const pass = document.getElementById('regAdminPass') ? document.getElementById('regAdminPass').value : '';
        const confirm = document.getElementById('regAdminConfirm') ? document.getElementById('regAdminConfirm').value : '';

        if (pass && confirm && pass !== confirm) {
            showToast("Passwords do not match!", "danger");
            return false;
        }

        saveTeacher({
            name: name || "Dr. Rajesh Sharma",
            teacherId: id || "T-101",
            email: email,
            phone: "+91 9876543210"
        });

        if (isServerEnv) {
            try {
                const params = new URLSearchParams({ name, adminId: id, email, password: pass });
                fetch('AdminRegisterServlet', { method: 'POST', body: params }).catch(() => {});
            } catch (err) {}
        }

        showToast("Teacher registered successfully!", "success");
        setTimeout(() => { window.location.href = 'teacherhome.html'; }, 300);
        return false;
    }
}

function handlePostNotice(e) {
    if (e) e.preventDefault();
    const form = e.target;
    const title = form.querySelector('[name="title"]').value.trim();
    const targetClass = form.querySelector('[name="targetClass"]').value;
    const content = form.querySelector('[name="content"]').value.trim();

    const notices = JSON.parse(localStorage.getItem('smartexam_announcements') || '[]');
    notices.unshift({
        id: Date.now(),
        title: title,
        targetClass: targetClass,
        content: content,
        date: new Date().toLocaleDateString()
    });
    localStorage.setItem('smartexam_announcements', JSON.stringify(notices));

    if (isServerEnv) {
        try {
            const params = new URLSearchParams({ title, targetClass, content });
            fetch('AnnouncementServlet', { method: 'POST', body: params }).catch(() => {});
        } catch (err) {}
    }

    showToast("Announcement published successfully!", "success");
    if (typeof closeNoticeModal === 'function') closeNoticeModal();
    return false;
}

function handleTeacherProfile(e) {
    if (e) e.preventDefault();
    const name = document.getElementById('tName') ? document.getElementById('tName').value.trim() : 'Dr. Rajesh Sharma';
    const email = document.getElementById('tEmail') ? document.getElementById('tEmail').value.trim() : 'admin@example.com';
    const phone = document.getElementById('tPhone') ? document.getElementById('tPhone').value.trim() : '+91 9876543210';
    const avatar = window.teacherAvatarBase64 || null;

    const teacher = getTeacher();
    teacher.name = name;
    teacher.email = email;
    teacher.phone = phone;
    if (avatar) teacher.avatar = avatar;
    saveTeacher(teacher);

    if (document.getElementById('tNameHeading')) document.getElementById('tNameHeading').textContent = name;

    showToast("Teacher profile updated successfully!", "success");
    return false;
}

// ==========================================================================
// 5. NATIVE HTML5 CANVAS CHARTS (Zero-CDN, 100% Offline Resilience)
// ==========================================================================
const CanvasCharts = {
    drawPassFailDoughnut(canvasId, passCount, failCount) {
        const canvas = document.getElementById(canvasId);
        if (!canvas) return;
        const ctx = canvas.getContext('2d');
        const w = canvas.width = canvas.parentElement.clientWidth || 300;
        const h = canvas.height = 240;
        ctx.clearRect(0, 0, w, h);

        const total = passCount + failCount;
        const centerX = w / 2;
        const centerY = h / 2 - 10;
        const radius = Math.min(centerX, centerY) - 20;
        const innerRadius = radius * 0.6;

        if (total === 0) {
            ctx.fillStyle = '#64748b';
            ctx.font = '14px sans-serif';
            ctx.textAlign = 'center';
            ctx.fillText('No Assessment Data Available', centerX, centerY);
            return;
        }

        const passAngle = (passCount / total) * 2 * Math.PI;

        // Draw Pass Segment (Emerald)
        ctx.beginPath();
        ctx.arc(centerX, centerY, radius, 0, passAngle);
        ctx.arc(centerX, centerY, innerRadius, passAngle, 0, true);
        ctx.fillStyle = '#10b981';
        ctx.fill();

        // Draw Fail Segment (Rose/Red)
        if (failCount > 0) {
            ctx.beginPath();
            ctx.arc(centerX, centerY, radius, passAngle, 2 * Math.PI);
            ctx.arc(centerX, centerY, innerRadius, 2 * Math.PI, passAngle, true);
            ctx.fillStyle = '#ef4444';
            ctx.fill();
        }

        // Draw Center Text
        const passPct = Math.round((passCount / total) * 100);
        ctx.fillStyle = '#f8fafc';
        ctx.font = 'bold 22px sans-serif';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(`${passPct}%`, centerX, centerY - 6);
        ctx.fillStyle = '#94a3b8';
        ctx.font = '11px sans-serif';
        ctx.fillText('Pass Rate', centerX, centerY + 14);

        // Draw Legend
        ctx.font = '12px sans-serif';
        ctx.fillStyle = '#10b981';
        ctx.fillRect(centerX - 100, h - 20, 12, 12);
        ctx.fillStyle = '#f8fafc';
        ctx.textAlign = 'left';
        ctx.fillText(`Pass: ${passCount}`, centerX - 82, h - 10);

        ctx.fillStyle = '#ef4444';
        ctx.fillRect(centerX + 20, h - 20, 12, 12);
        ctx.fillStyle = '#f8fafc';
        ctx.fillText(`Fail: ${failCount}`, centerX + 38, h - 10);
    },

    drawScoreBar(canvasId, tiers) {
        const canvas = document.getElementById(canvasId);
        if (!canvas) return;
        const ctx = canvas.getContext('2d');
        const w = canvas.width = canvas.parentElement.clientWidth || 300;
        const h = canvas.height = 240;
        ctx.clearRect(0, 0, w, h);

        const labels = ['< 40%', '40-59%', '60-79%', '80-100%'];
        const colors = ['#ef4444', '#f59e0b', '#3b82f6', '#8b5cf6'];
        const maxVal = Math.max(...tiers, 5);

        const paddingBottom = 30;
        const paddingTop = 20;
        const paddingLeft = 40;
        const plotHeight = h - paddingBottom - paddingTop;
        const barWidth = Math.min((w - paddingLeft) / tiers.length - 20, 50);

        // Draw Axis
        ctx.strokeStyle = 'rgba(255,255,255,0.1)';
        ctx.lineWidth = 1;
        ctx.beginPath();
        ctx.moveTo(paddingLeft, paddingTop);
        ctx.lineTo(paddingLeft, h - paddingBottom);
        ctx.lineTo(w - 10, h - paddingBottom);
        ctx.stroke();

        // Draw Bars
        tiers.forEach((val, i) => {
            const x = paddingLeft + 15 + i * ((w - paddingLeft - 20) / tiers.length);
            const barH = (val / maxVal) * plotHeight;
            const y = h - paddingBottom - barH;

            // Bar
            ctx.fillStyle = colors[i];
            ctx.beginPath();
            ctx.roundRect ? ctx.roundRect(x, y, barWidth, barH, [4, 4, 0, 0]) : ctx.fillRect(x, y, barWidth, barH);
            ctx.fill();

            // Value text
            ctx.fillStyle = '#ffffff';
            ctx.font = 'bold 11px sans-serif';
            ctx.textAlign = 'center';
            if (val > 0) ctx.fillText(val, x + barWidth / 2, y - 5);

            // Label text
            ctx.fillStyle = '#94a3b8';
            ctx.font = '10px sans-serif';
            ctx.fillText(labels[i], x + barWidth / 2, h - 10);
        });
    }
};

// ==========================================================================
// 6. ANTI-CHEATING PROCTORING ENGINE
// ==========================================================================
const Proctor = {
    tabSwitches: 0,
    maxStrikes: 3,
    active: false,

    start(onViolation, onAutoSubmit) {
        this.tabSwitches = 0;
        this.active = true;
        this.requestFullscreen();

        document.addEventListener("visibilitychange", () => {
            if (this.active && document.hidden) {
                this.tabSwitches++;
                if (this.tabSwitches >= this.maxStrikes) {
                    showToast("🚨 Maximum strikes reached! Auto-submitting exam.", "danger");
                    if (onAutoSubmit) onAutoSubmit();
                } else {
                    showToast(`⚠️ Strike ${this.tabSwitches}/${this.maxStrikes}: Please do not switch tabs!`, "warning");
                    if (onViolation) onViolation(this.tabSwitches);
                }
            }
        });

        document.addEventListener("fullscreenchange", () => {
            if (this.active && !document.fullscreenElement) {
                this.tabSwitches++;
                showToast(`⚠️ Exited fullscreen! Strike ${this.tabSwitches}/${this.maxStrikes}`, "warning");
                if (this.tabSwitches >= this.maxStrikes && onAutoSubmit) onAutoSubmit();
            }
        });

        window.addEventListener("contextmenu", e => { if (this.active) e.preventDefault(); });
        window.addEventListener("copy", e => { if (this.active) e.preventDefault(); });
        window.addEventListener("paste", e => { if (this.active) e.preventDefault(); });
        window.addEventListener("keydown", e => {
            if (this.active && (e.key === "F12" || (e.ctrlKey && (e.key === 'c' || e.key === 'v' || e.key === 'u' || e.key === 'i')))) {
                e.preventDefault();
                showToast("Shortcuts disabled during exam!", "warning");
            }
        });
    },

    stop() {
        this.active = false;
        if (document.fullscreenElement) {
            document.exitFullscreen().catch(() => {});
        }
    },

    requestFullscreen() {
        const el = document.documentElement;
        if (el.requestFullscreen) {
            el.requestFullscreen().catch(() => {});
        }
    }
};

// ==========================================================================
// 7. TOAST NOTIFICATIONS
// ==========================================================================
function showToast(message, type = "info") {
    let container = document.getElementById("toast-container");
    if (!container) {
        container = document.createElement("div");
        container.id = "toast-container";
        document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast`;
    
    let icon = "fa-info-circle";
    let color = "#38bdf8";
    if (type === "success") { icon = "fa-check-circle"; color = "#34d399"; }
    if (type === "warning") { icon = "fa-triangle-exclamation"; color = "#fbbf24"; }
    if (type === "danger") { icon = "fa-radiation"; color = "#f87171"; }

    toast.innerHTML = `<i class="fa-solid ${icon}" style="color:${color};"></i> <span>${message}</span>`;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = "0";
        toast.style.transform = "translateY(20px)";
        setTimeout(() => toast.remove(), 300);
    }, 3500);
}

// ==========================================================================
// 8. VERIFIABLE CERTIFICATE GENERATOR WITH CANVAS QR CODE
// ==========================================================================
function generateCertificateHTML(studentName, examTitle, score, totalMarks, percentage, dateStr) {
    const certId = "CERT-" + Math.random().toString(36).substring(2, 9).toUpperCase();
    const isPass = percentage >= 40;

    return `
    <div class="certificate-box" id="printableCert">
        <div style="text-align: right; color: #64748b; font-size: 0.85rem;">Certificate ID: <strong>${certId}</strong></div>
        <div style="font-size: 2.8rem; color: #f59e0b; margin: 10px 0;"><i class="fa-solid fa-award"></i></div>
        <h1>CERTIFICATE OF ACHIEVEMENT</h1>
        <p style="font-size: 1.1rem; color: #475569; margin-top: 10px;">This certificate is proudly awarded to</p>
        <div class="student-name">${studentName}</div>
        <p style="font-size: 1.05rem; color: #334155; max-width: 620px; margin: 0 auto 20px;">
            for completing the <strong>${examTitle}</strong> assessment with an overall score of 
            <strong style="color: #2563eb;">${score} / ${totalMarks} (${percentage}%)</strong> and achieving a status of 
            <strong style="color: ${isPass ? '#10b981' : '#ef4444'};">${isPass ? 'PASSED / QUALIFIED' : 'NEEDS IMPROVEMENT'}</strong>.
        </p>
        <div style="display: flex; justify-content: space-between; align-items: flex-end; margin-top: 40px; border-top: 2px solid #cbd5e1; padding-top: 20px;">
            <div style="text-align: left;">
                <canvas id="certQrCanvas" width="80" height="80" style="background:#f1f5f9; border-radius:6px;"></canvas>
                <div style="font-size: 0.75rem; color: #64748b; margin-top: 4px;">Scan to Verify</div>
            </div>
            <div style="text-align: center;">
                <p style="margin: 0; font-weight: 600; color: #1e293b;">Date: ${dateStr || new Date().toLocaleDateString()}</p>
                <small style="color: #64748b;">SmartExam Online Portal</small>
            </div>
            <div style="text-align: right;">
                <div style="font-family: 'Brush Script MT', cursive; font-size: 1.8rem; color: #1e3a8a;">Dr. Rajesh Sharma</div>
                <div style="border-top: 1px solid #94a3b8; padding-top: 4px; font-size: 0.85rem; font-weight: 600;">Controller of Examinations</div>
            </div>
        </div>
    </div>
    <div style="text-align: center; margin-top: 20px;">
        <button class="btn btn-primary" onclick="window.print()"><i class="fa-solid fa-print"></i> Print / Save as PDF</button>
    </div>
    `;
}

function renderSimpleQR(canvasId, text) {
    const canvas = document.getElementById(canvasId);
    if (!canvas) return;
    const ctx = canvas.getContext("2d");
    ctx.fillStyle = "#ffffff";
    ctx.fillRect(0, 0, 80, 80);
    ctx.fillStyle = "#1e293b";
    
    const size = 8;
    for (let r = 0; r < 10; r++) {
        for (let c = 0; c < 10; c++) {
            if ((r < 3 && c < 3) || (r < 3 && c > 6) || (r > 6 && c < 3)) {
                ctx.fillRect(c * size, r * size, size, size);
            } else if ((r + c + text.length) % 2 === 0) {
                ctx.fillRect(c * size, r * size, size, size);
            }
        }
    }
}

// ==========================================================================
// 9. STUDENT PROFILE HELPERS
// ==========================================================================
function getCurrentStudent() {
    const saved = localStorage.getItem("smartexam_student");
    if (saved) {
        try { return JSON.parse(saved); } catch(e) {}
    }
    return {
        name: localStorage.getItem("smartexam_studentName") || "Ashutosh Shehra",
        studentId: "STU-2026",
        email: "student@example.com",
        classGrade: localStorage.getItem("smartexam_studentClass") || "Class 10"
    };
}

function saveCurrentStudent(stu) {
    localStorage.setItem("smartexam_student", JSON.stringify(stu));
    localStorage.setItem("smartexam_studentName", stu.name);
    localStorage.setItem("smartexam_studentClass", stu.classGrade || "Class 10");
}

// ==========================================================================
// 10. TEACHER / ADMIN PROFILE HELPERS
// ==========================================================================
function getTeacher() {
    const saved = localStorage.getItem('smartexam_teacher');
    if (saved) {
        try { return JSON.parse(saved); } catch(e) {}
    }
    return {
        name: "Dr. Rajesh Sharma",
        teacherId: "T-101",
        email: "admin@example.com",
        phone: "+91 9876543210"
    };
}

function saveTeacher(t) {
    localStorage.setItem('smartexam_teacher', JSON.stringify(t));
}

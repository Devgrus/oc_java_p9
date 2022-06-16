db = db.getSiblingDB('mediscreen');

db.createCollection('history');

db.history.insertMany([
 {
    id: ObjectId(),
    patId: 1,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il « se sent très bien »"
  },
  {
    id: ObjectId(),
    patId: 1,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il se sent fatigué pendant la journée"
  },
  {
    id: ObjectId(),
    patId: 1,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il ne se sent pas si fatigué que ça"
  },
  {
    id: ObjectId(),
    patId: 2,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il ressent beaucoup de stress au travail"
  },
  {
    id: ObjectId(),
    patId: 2,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois"
  },
  {
    id: ObjectId(),
    patId: 2,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Tests de laboratoire indiquant une microalbumine élevée"
  },
  {
    id: ObjectId(),
    patId: 2,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare que tout semble aller bien"
  },
  {
    id: ObjectId(),
    patId: 3,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il fume depuis peu"
  },
  {
    id: ObjectId(),
    patId: 3,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Tests de laboratoire indiquant une microalbumine élevée"
  },
  {
    id: ObjectId(),
    patId: 3,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière"
  },
  {
    id: ObjectId(),
    patId: 3,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"
  },
  {
    id: ObjectId(),
    patId: 4,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il lui est devenu difficile de monter les escaliers"
  },
  {
    id: ObjectId(),
    patId: 4,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"
  },
  {
    id: ObjectId(),
    patId: 4,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir commencé à fumer depuis peu"
  },
  {
    id: ObjectId(),
    patId: 5,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir des douleurs au cou occasionnellement"
  },
  {
    id: ObjectId(),
    patId: 5,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite."
  },
  {
    id: ObjectId(),
    patId: 5,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles"
  },
  {
    id: ObjectId(),
    patId: 5,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite."
  },
  {
    id: ObjectId(),
    patId: 6,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il se sent bien"
  },
  {
    id: ObjectId(),
    patId: 6,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il se sent bien"
  },
  {
    id: ObjectId(),
    patId: 7,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il se réveille souvent avec une raideur articulaire"
  },
  {
    id: ObjectId(),
    patId: 8,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Les tests de laboratoire indiquent que les anticorps sont élevés"
  },
  {
    id: ObjectId(),
    patId: 9,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires"
  },
  {
    id: ObjectId(),
    patId: 9,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée"
  },
  {
    id: ObjectId(),
    patId: 9,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient signale qu'il est facilement irrité par des broutilles"
  },
  {
    id: ObjectId(),
    patId: 10,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il n'a aucun problème"
  },
  {
    id: ObjectId(),
    patId: 10,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il n'a aucun problème"
  },
  {
    id: ObjectId(),
    patId: 10,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il n'a aucun problème"
  },
  {
    id: ObjectId(),
    patId: 10,
    createdDate: new Date(),
    lastModifiedDate: new Date(),
    note: "Le patient déclare qu'il n'a aucun problème"
  },
]);
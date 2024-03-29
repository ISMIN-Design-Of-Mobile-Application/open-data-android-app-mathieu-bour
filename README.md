## Fête de la science
[![Build Status](https://img.shields.io/github/workflow/status/ISMIN-Design-Of-Mobile-Application/open-data-android-app-mathieu-bour/Android%20CI?style=flat-square)](https://github.com/ISMIN-Design-Of-Mobile-Application/open-data-android-app-mathieu-bour/actions?query=workflow%3A%22Android+CI%22)

Codé par : Mathieu Bour <mathieu.bour@etu.emse.fr>

## Description  

- URL des données brutes : sur [data.gouv.fr](https://www.data.gouv.fr/fr/datasets/programme-de-la-fete-de-la-science-2019)
- URL des données filtrées : [dataset.json](https://storage.googleapis.com/public.mathieu-bour.fr/projects/emse-3a-android/dataset.json)
- URL des données filtrées (extrait) : [dataset.excerpt.json](https://storage.googleapis.com/public.mathieu-bour.fr/projects/emse-3a-android/dataset.excerpt.json)

Exemple de donnée :

```json
{
  "id": "35892009",
  "name": "Conférence \"Les glaciers de montagne face aux changements climatiques\"",
  "description": "Les glaciers de montagne subissent des changements très importants et facilement identifiables depuis quelques décennies, qui témoignent des changements climatiques.",
  "conditions": "Entrée libre",
  "image": "https://cibul.s3.amazonaws.com/54a91f0909fe46cfaefa30d3600184e4.base.image.jpg",
  "thumb": "https://cibul.s3.amazonaws.com/54a91f0909fe46cfaefa30d3600184e4.thumb.image.jpg",
  "organization": "ALTEC",
  "address": "Rue Henri de Boissieu 01000 Bourg-en-Bresse",
  "city": "Bourg-en-Bresse",
  "latitude": 46.214168,
  "longitude": 5.244869,
  "location_description": "Plus d'une trentaine de stands d'animation scientifique, un programme riche de conférences et de projections, la visite du Technopole Alimentec... Découvrez la programmation très riche de cette nouvelle édition du village des sciences de Bourg-en-Bresse !\n\nPROGRAMME :\nVendredi 4 octobre de 18h à 21h : inauguration départementale et conférence sur l'innovation médicale avec le Centre Hospitalier de Bourg-en-Bresse\nSamedi 5 octobre (14h à 18h) et dimanche 6 octobre (10h à 18h) : stands, conférences, projections, visite...\n\nINFOS PRATIQUES :\nBuvette équitable avec Artisans du Monde le samedi et dimanche\nNOUVEAU : Restauration rapide et locale avec le Foodtruck fermier le dimanche midi !",
  "dates": "samedi 5 octobre - 17h00 à 18h00",
  "telephone": "04 74 45 52 17",
  "permalink": "https://openagenda.com/fetedelascience2019_aura/event/conference-les-glaciers-de-montagne-face-aux-changements-climatiques"
}
```

Lorsque l'application est démarrée pour la première fois, la liste des évènements est téléchargée
à l'aide de Retrofit et insérée dans la base de données locale avec Room.
Lors des appels suivant, les données de la base de données sont toujours réaffichées en premier afin
d'éviter d'avoir un écran vide pendant de le chargement.

Les images sont affichées avec Picasso, qui se charge lui-même de la mise en cache des images.

## Librairies externes

- [Retrofit 2](https://square.github.io/retrofit/) : client HTTP basé sur des interfaces
- [Picasso](https://square.github.io/picasso/) : affichage simplifié des images
- [Google Maps et Google Play Services](https://developers.google.com/maps/documentation/android-sdk/intro) : affichage d'une carte Google Maps
- [Room](https://developer.android.com/topic/libraries/architecture/room) : persistence des données de l'API
- [Google OSS Licenses](https://github.com/google/play-services-plugins) : affichage des licences tierces
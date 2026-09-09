from django.urls import path

from . import views

urlpatterns = [
    path("products", views.products),
]

# Add any other URL you need here.

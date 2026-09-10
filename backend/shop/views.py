from django.http import JsonResponse

from .models import Product

PRODUCT_FIELDS = ("id", "title", "subtitle", "description", "image", "price_ore")


def products(request):
    """GET /api/products -> {"products": [...]}"""
    return JsonResponse({"products": list(Product.objects.values(*PRODUCT_FIELDS))})


# TODO: A view that creates an order.

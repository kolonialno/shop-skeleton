from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt


def products(request):
    """GET /api/products -> {"products": [...]}"""
    # TODO: Implement
    return JsonResponse({"products": []})


# Add any other view you need here.

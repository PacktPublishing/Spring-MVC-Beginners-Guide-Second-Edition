<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section>
	<div class="jumbotron">
		<div class="container">
			<h1 class="alert alert-danger">Invalid promo code</h1>
		</div>
	</div>
</section>

<section>
	<div class="container">
		<p>
			<a href="<spring:url value="/market/products" />"
				class="btn btn-primary"> <span
				class="glyphicon-hand-left glyphicon"></span> products
			</a>
		</p>
	</div>

</section>
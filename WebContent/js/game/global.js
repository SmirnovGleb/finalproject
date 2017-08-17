jQuery(function($){
	$('.pokerchip').on('click', function(e){
		e.preventDefault();
		$(this).addClass('active').siblings().removeClass('active');
		$('[name="bet"]').val($(this).text());
	});

	$('form').submit(function(e){
		if ($('[name="bet"]').val() == '') {
			e.preventDefault();
			alert('Нужно сделать ставку');
		}
		if ($('[name="positions"]').val() == '') {
			e.preventDefault();
			alert('Нужно Выбрать поле');
		}
	});
});

$(window).on('load', function() {
    start_roulette();
});
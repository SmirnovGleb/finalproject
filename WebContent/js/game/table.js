(function($) {
	
	// table
	(function() {
		"use strict"
		
		function getButtonCells(btn) {
			var cells = btn.data('cells');
			if (!cells || !cells.length) {
				cells = [];
				switch (btn.data('type')) {
					case 'sector':
						var nums = sectors[btn.data('sector')];
						for (var i = 0, len = nums.length; i < len; i++) {
							cells.push(table_nums[nums[i]]);
						}
						return cells;
						break;
					case 'num':
					default:
						var nums = String(btn.data('num')).split(',');
						for (var i = 0, len = nums.length; i < len; i++) {
							cells.push(table_nums[nums[i]]);
						}
						btn.data('cells', cells)
						return btn.data('cells');
						break;
				}
			}
			return cells;
		};

		// props
		var active = true,
			selectors = {
				roulette : '.roulette',
				num : '.num',
				sector : '.sector',
				table_btns : '.controlls .table-btn'
			},
			classes = {
				red : 'red',
				black : 'black',
				green : 'green',
				hover : 'hover',
				active : 'active'
			},
			numbers = {
				red : [],
				black : [],
				green : []
			},
			sectors = {
				'1' : [], // 1st row
				'2' : [], // 2nd row
				'3' : [], // 3rd row
				'4' : [], // 1st 12
				'5' : [], // 2nd 12
				'6' : [], // 3rd 12
				'7' : [], // 1 to 18
				'8' : [], // EVEN
				'9' : [], // RED
				'10' : [], // BLACK
				'11' : [], // ODD
				'12' : [], // 19 to 36
			},
			table_nums = {},
			table_sectors = {};

		// init
		$(selectors.num).each(function() {
			var $this = $(this),
				color,
				num = Number($this.text());
			// add to instances array
			table_nums[num] = $this;
			// add to colors array
			for (var color in numbers) {
				if ($this.hasClass(classes[color])) {
					numbers[color].push(num);
					$this.data('color', color);
				}
			}
		})

		$(selectors.sector).each(function() { 
			var $this = $(this),
				color;
			if ($this.hasClass(classes.red)) {
				color = 'red';
			} else if ($this.hasClass(classes.black)) {
				color = 'black';
			} else {
				color = 'sector';
			}
			$this.data('color', color);
			table_sectors[$this.data('sector')] = $this;
		});

		// sort numbers
		for (var color in numbers) {
			numbers[color].sort(function(a, b) { return a - b; });
		}

		// populate sectors
		for (var i = 1; i <= 36; i++) {
			// 1st row, 2nd row, 3rd row
			switch (i%3) {
				case 0:
					sectors['1'].push(i);
					break;
				case 1:
					sectors['3'].push(i);
					break;
				case 2:
					sectors['2'].push(i);
					break;
			}

			// 1st 12, 2nd 12, 3rd 12
			if (i <= 12) {
				sectors['4'].push(i);
			} else if (i <= 24) {
				sectors['5'].push(i);
			} else {
				sectors['6'].push(i);
			}

			// 1 to 18, 19 to 36
			if (i <= 18) {
				sectors['7'].push(i);
			} else {
				sectors['12'].push(i);
			}

			// ODD, EVEN
			if (i%2) {
				sectors['8'].push(i);
			} else {
				sectors['11'].push(i);
			}

			if (numbers.red.indexOf(i) != -1) {
				sectors['9'].push(i);
			} else if (numbers.black.indexOf(i) != -1) {
				sectors['10'].push(i);
			}
		}

		// buttons
		var table_btns = $(selectors.table_btns).hover(
			function() {
				if (active) {
					var $this = $(this),
						cells = getButtonCells($this);
					for (var i = 0, len = cells.length; i < len; i++) {
						cells[i].addClass(classes.hover);
					}
					var sector = $this.data('sector');
					if (sector) {
						table_sectors[sector].addClass(classes.hover);
					}
				}
			},
			function() {
				var $this = $(this),
					cells = getButtonCells($this);
				for (var i = 0, len = cells.length; i < len; i++) {
					cells[i].removeClass(classes.hover);
				}
				var sector = $this.data('sector');
				if (sector) {
					table_sectors[sector].removeClass(classes.hover);
				}
			}
		).click(function() {
			// console.log(String($(this).data('num')).split(','));
			$('[name="positions"]').val($(this).data('num'));
			if (active) {

				$('.num').removeClass('active');
				$('.sector').removeClass('active');

				var $this = $(this),
					cells = getButtonCells($this);
				for (var i = 0, len = cells.length; i < len; i++) {
					cells[i].addClass(classes.active);
				}
				var sector = $this.data('sector');
				if (sector) {
					table_sectors[sector].addClass(classes.active);
				}
			}
		});

		/*console.log('1st row: ' + sectors['1']);
		console.log('2nd row: ' + sectors['2']);
		console.log('3rd row: ' + sectors['3']);
		console.log('1st 12: ' + sectors['4']);
		console.log('2nd 12: ' + sectors['5']);
		console.log('3rd 12: ' + sectors['6']);
		console.log('1-18: ' + sectors['7']);
		console.log('even: ' + sectors['8']);
		console.log('red: ' + sectors['9']);
		console.log('black: ' + sectors['10']);
		console.log('odd: ' + sectors['11']);
		console.log('19-36: ' + sectors['12']);
		console.log('numbers.green: ' + numbers.green);
		console.log('numbers.red: ' + numbers.red);
		console.log('numbers.black: ' + numbers.black);*/
	})();
	
	// spinner
	(function() {
		"use strict"
		
		// var table = $('.spinner'),
		// 	ballHolder = $('.ball-holder'),
		// 	ball = ballHolder.find('.ball'),
		// 	center = {
		// 		x: table.offset().left + table.width() / 2,
		// 		y: table.offset().top + table.height() / 2
		// 	},
		// 	circumference = (table.width() - ball.width())  * Math.PI,
		// 	spinStartTime,
		// 	bettingTime = 10 * 1000, // msecs
		// 	spinTotalTime = bettingTime + 10 * 1000, // msecs
		// 	spinSpeed,
		// 	updateTime = 20, // msecs
		// 	distanceTotal,
		// 	spinTimeout,
		// 	spinning = false;

		function startSpin() {
			console.log('startSpin');
			if (!spinning) {
				spinning = true;
				spinStartTime = new Date().getTime();
				spinSpeed = circumference/100*3;
				distanceTotal = 0;
				spinTimeout = setInterval(updateBall, updateTime);
			}
		}

		function updateBall() {
			var time = new Date().getTime() - spinStartTime;
			if (time < spinTotalTime) {
				var speed;
				if (time < bettingTime) {
					speed = linear(bettingTime, spinSpeed, spinSpeed, bettingTime);
					if (time + updateTime >= bettingTime) {
						console.log('no more betting');
					}
				} else {
					speed = $.easing.easeInOutQuad(null, time - bettingTime, spinSpeed, -spinSpeed, spinTotalTime - bettingTime);
				}
				//console.log('speed=' + speed);
				distanceTotal += speed;
				ballHolder.css('transform', 'rotate(' + (-(360 - (distanceTotal % circumference) / circumference * 360)) + 'deg)');
			} else {
				onSpinEnd();
				clearTimeout(spinTimeout);
				return 0;
			}
		}

		function onSpinEnd() {
			console.log('onSpinEnd');
			clearTimeout(spinTimeout);
			spinning = false;
		}

		function linear(t, b, c, d) {
			//return t/d*Math.abs(b-c);
			return c*t/d + b;
		}

		function rotate(x, y) {
			return Math.atan2(y - center.y, x - center.x) * 180 / Math.PI;
		}

		function mousedown(event) {
			event.preventDefault();
			$('body').addClass('moving');
			mousemove(event);
			$(document).on('mousemove', mousemove)
				.on('mouseup', mouseup);
		}

		function mousemove(event) {
			ballHolder.css('transform', 'rotate(' + rotate(event.pageX, event.pageY) + 'deg)');
		}

		function mouseup() {
			$('body').removeClass('moving');
			$(document).off('mouseup', mouseup)
				.off('mousemove', mousemove);
		}

	// 	// init
	// 	/*ball.on('mousedown', mousedown);

	// 	table.on('mousedown', function(event){
	// 		if (event.target == this) {
	// 			mousedown(event);
	// 		}
	// 	});*/
		// table.on('click', function(event) {
		// 	startSpin();
		// });
	})();

})(jQuery);
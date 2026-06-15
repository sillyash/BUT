# Global variables
THRESH = (200, 255)				# Detection threshold (color)

MIN_AREA_RATIO 	= 2900		# Minimum rect area (ratio from resolution) for mask
MERGE_RATIO 		= 500000	# Maximum distance between two rectangles, below they get merged
PERSIST_RATIO		= 600000	# Maximum distance between two rectangles, below the rectangle persists between frames

AVAILABLE_EXTS = ['.mp4', '.avi', '.mkv'] # Accepted file extensions

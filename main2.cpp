#include < unistd.h >
#include < stdio.h >

void dir (void)
{
	printf (“Dir: %s\n”, get_current_dir_name ());
} 
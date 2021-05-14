#include <stdio.h> 
#include <string.h>

int main(int argc, char const *argv[])
{
	char x = 'q';
	char str[10000] = "123";
	strncat(str, &x, 1); 
	printf("sd\n");
	printf("%s\n", str);
	return 0;
}
run:
	make -C PlaygroundApi
	make -C FrameworkCore
	java --module-path ./FrameworkCore/target/classes:./PlaygroundApi/target/classes --add-modules FrameworkCore,PlaygroundApi com.framework.Main

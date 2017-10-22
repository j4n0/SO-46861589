run:
	make -C PlaygroundApi
	make -C FrameworkCore
	echo Reading resource in PlaygroundApi from FrameworkCore
	java --module-path ./FrameworkCore/target/classes:./PlaygroundApi/target/classes --add-modules FrameworkCore,PlaygroundApi com.framework.Main
	echo Reading resource in PlaygroundApi from PlaygroundApi
	java --module-path ./PlaygroundApi/target/classes --add-modules PlaygroundApi com.playground.api.App

clean:
	make -C PlaygroundApi clean
	make -C FrameworkCore clean

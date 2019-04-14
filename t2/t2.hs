import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

rainbowPalette :: Int -> [(Int,Int,Int)]
rainbowPalette n = take n $ cycle [(255,0,0),(255,102,0),(255,255,0),(153,255,51),(51,204,51),(102,255,153),(0,255,255),(51,153,255),(0,51,204),(153,102,255),(255,0,255),(255,0,102)]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,80+i*5,0) | i <- [0..n] ]

redPalette :: Int -> [(Int,Int,Int)]
redPalette n = [(80+i*5,0,0) | i <- [0..n] ]

bluePalette :: Int -> [(Int,Int,Int)]
bluePalette n = [(0,0,80+i*5) | i <- [0..n] ]

varPalette :: Int -> [(Int,Int,Int)]
varPalette n = [(10+i,20+j,30+z) | i <- [0..n] ,j <- [0..n] ,z <- [0..n] ]

-------------------------------------------------------------------------------

greenPalette2 :: Int -> [(Int,Int,Int)]
greenPalette2 n = [(0,255,0) | i <- [0..n] ]

redPalette2 :: Int -> [(Int,Int,Int)]
redPalette2 n = [(255,0,0) | i <- [0..n] ]

bluePalette2 :: Int -> [(Int,Int,Int)]
bluePalette2 n = [(0,0,255) | i <- [0..n] ]

-------------------------------------------------------------------------------
-- Caso 1
-------------------------------------------------------------------------------

genRectsInLine :: Int -> Int -> [Rect]
genRectsInLine a n  = [((m*(w+gap),l*(gap+h)),w,h) | m <- [0..fromIntegral (n-1)] ,l <- [0..fromIntegral (a-1)]]
  where (w,h) = (50,50)
        gap = 10

-------------------------------------------------------------------------------
-- Caso 2
-------------------------------------------------------------------------------

genCirclesInCircles :: Int -> [Circle]
genCirclesInCircles n = [((x+((r+80)*cos(fromIntegral a)),y+((r+80)*sin(fromIntegral a))),r) | a <- [0,n..360]]
  where (x,y) = (160,160)
        r = 20
-------------------------------------------------------------------------------
-- Caso 3
-------------------------------------------------------------------------------

genCirclesInLine2 :: Int -> Int -> [Circle]
genCirclesInLine2 a n = [((100+m*x,100+y*l),r) | m <- [0..fromIntegral (n-1)] ,l <- [0..fromIntegral (a-1)] ]
  where r = 30
        (x,y) = (150,150)

genCirclesInLine3 :: Int -> Int -> [Circle]
genCirclesInLine3 a n = [((80+m*x,130+y*l),r) | m <- [0..fromIntegral (n-1)] ,l <- [0..fromIntegral (a-1)] ]
  where r = 30
        (x,y) = (150,150)

genCirclesInLine4 :: Int -> Int -> [Circle]
genCirclesInLine4 a n = [((120+m*x,130+y*l),r) | m <- [0..fromIntegral (n-1)] ,l <- [0..fromIntegral (a-1)] ]
  where r = 30
        (x,y) = (150,150)

-------------------------------------------------------------------------------
-- Caso 4
-------------------------------------------------------------------------------

genCirclesFunc1 :: Int -> [Circle]
genCirclesFunc1 n = [((30*m - 300,100+80*sin(50*m)),r) | m <- [13..fromIntegral (n+13)]]
  where r = 20

genCirclesFunc2 :: Int -> [Circle]
genCirclesFunc2 n = [((30*m - 300,200+80*sin(50*m)),r) | m <- [13..fromIntegral (n+13)]]
  where r = 20

genCirclesFunc3 :: Int -> [Circle]
genCirclesFunc3 n = [((30*m -300,300+80*sin(50*m)),r) | m <- [13..fromIntegral (n+13)]]
  where r = 20


-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------

-- Gera string representando retângulo SVG 
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo
svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

-- Gera string representando circulos SVG 
-- dadas coordenadas e dimensoes do circulo e uma string com atributos de estilo
svgCircle :: Circle -> String -> String 
svgCircle ((x,y),r) style =
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" x y r style

-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

-------------------------------------------------------------------------------
-- Função principal que gera arquivo com imagem SVG
-------------------------------------------------------------------------------

genCase1 :: IO ()
genCase1 = do
  writeFile "case1.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRectsInLine nlines nrects
        palette = greenPalette nrects
        nlines = 5
        nrects = 49
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase2 :: IO ()
genCase2 = do
  writeFile "case2.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genCirclesInCircles (360 `div` alfa)
        palette = rainbowPalette (330 `div` alfa)
        alfa = 30
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase3 :: IO ()
genCase3 = do
  writeFile "case3.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgfigs2 ++ svgfigs3 ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genCirclesInLine2 2 3
        palette = bluePalette2 6
        svgfigs2 = svgElements svgCircle circles2 (map svgStyle palette2)
        circles2 = genCirclesInLine3 2 3
        palette2 = redPalette2 6
        svgfigs3 = svgElements svgCircle circles3 (map svgStyle palette3)
        circles3 = genCirclesInLine4 2 3
        palette3 = greenPalette2 6
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase4 :: IO ()
genCase4 = do
  writeFile "case4.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgfigs2 ++ svgfigs3 ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genCirclesFunc1 20
        palette = redPalette 18
        svgfigs2 = svgElements svgCircle circles2 (map svgStyle palette2)
        circles2 = genCirclesFunc2 20
        palette2 = greenPalette 18
        svgfigs3 = svgElements svgCircle circles3 (map svgStyle palette3)
        circles3 = genCirclesFunc3 20
        palette3 = bluePalette 18
        (w,h) = (1500,500) -- width,height da imagem SVG


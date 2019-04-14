import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)


-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rainbowPalette :: Int -> [(Int,Int,Int)]
rainbowPalette n = take n $ cycle [(255,0,0),(255,102,0),(255,255,0),(153,255,51),(51,204,51),(102,255,153),(0,255,255),(51,153,255),(0,51,204),(153,102,255),(255,0,255),(255,0,102)]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,20+i*5,0) | i <- [0..n] ]

varPalette :: Int -> [(Int,Int,Int)]
varPalette n = [(10+i,20+j,30+z) | i <- [0..n] ,j <- [0..n] ,z <- [0..n] ]

-------------------------------------------------------------------------------
-- Geração de retângulos em suas posições
-------------------------------------------------------------------------------

genRectsInLine :: Int -> Int -> [Rect]
genRectsInLine a n  = [((m*(w+gap),l*(gap+h)),w,h) | m <- [0..fromIntegral (n-1)] ,l <- [0..fromIntegral (a-1)]]
  where (w,h) = (50,50)
        gap = 10

genCirclesInCircles :: Int -> [Circle]
genCirclesInCircles n = [((x+((r+80)*cos(fromIntegral a)),y+((r+80)*sin(fromIntegral a))),r) | a <- [0,n..360]]
  where (x,y) = (160,160)
        r = 20

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
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d)" r g b

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

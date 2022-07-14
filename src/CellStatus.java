public enum CellStatus {
    CORRECT {
        @Override
        public String toString() {return "C";}
    },
    WRONG_CELL {
        @Override
        public String toString() {return "W";}
    },
    INCORRECT {
        @Override
        public String toString() {return "I";}
    },
    UNGUESSED {
        @Override
        public String toString() {return "U";}
    }   
}

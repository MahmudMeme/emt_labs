import axios from "../costum-axios/axios";

const bookRepository = {

    fetchBooks: () => {
        return axios.get("/books")
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    addBook: (name, author, category, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    },
    fetchCategories: () => {
        return axios.get("/books/categories")
    },
    fetchRentBook:(id)=>{
        return axios.post(`/books/rent/${id}`);
    },
    fetchMarkUsability:(id)=>{
        return axios.post(`/books/changeUsable/${id}`);
    }
}

export default bookRepository;
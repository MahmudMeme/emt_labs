import logo from '../../logo.svg';
import './App.css';
import React, {Component} from "react";
import bookRepository from "../../repository/bookRepository";
import {BrowserRouter as Router, Redirect, Route, Routes, Outlet, Navigate} from 'react-router-dom'
import bookList from "../book/bookList";
import Test from "../book/Test";
import Author from "../author/Authors";
import BookList from "../book/bookList";
import BookAdd from "../book/BookAdd";
import Categories from "../categories/categories";
import Header from "../Header/header";
import BookEdit from "../book/bookEdit";


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            selectedBook: {},
            authors: [],
            categories: []
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route
                                path="/authors"
                                element={<Author authors={this.state.authors}/>}
                            />
                            <Route
                                path="/categories"
                                element={<Categories categories={this.state.categories}/>}
                            />

                            <Route path={"/books/add"}
                                   element={<BookAdd
                                       authors={this.state.authors}
                                       categories={this.state.categories}
                                       onAddProduct={this.addBook}/>}
                            />
                            <Route path={"/books/edit/:id"}
                                   element={<BookEdit
                                       authors={this.state.authors}
                                       categories={this.state.categories}
                                       book={this.state.selectedBook}
                                       onEditBook={this.editBook}/>}
                            />

                            <Route
                                path="/books"
                                element={<BookList books={this.state.books}
                                                   onDelete={this.deleteBook}
                                                   onEdit={this.getBook}
                                                   onRent={this.rentBook}
                                                   onMarkUsability={this.markUsability}
                                />}
                                // element={<Test books={this.state.books}/>}
                            />
                            {/*<Route path="/" element={<Outlet/>}/>*/}
                            <Route path="/" element={<Navigate replace to="/books"/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
    }

    loadAuthors = () => {
        bookRepository.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }
    loadCategories = () => {
        bookRepository.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    loadBooks = () => {
        bookRepository.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
    }
    deleteBook = (id) => {
        bookRepository.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }
    getBook = (id) => {
        bookRepository.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    editBook = (id, name, category, author, availableCopies) => {
        bookRepository.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    addBook = (name, author, category, availableCopies) => {
        bookRepository.addBook(name, author, category, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    rentBook = (id) => {
        bookRepository.fetchRentBook(id)
            .then(() => {
                    this.loadBooks();
                }
            )
    }
    markUsability = (id) => {
        bookRepository.fetchMarkUsability(id)
            .then(() => {
                this.loadBooks();
            })
    }
}

export default App;
